package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    void lifeCycleTest() {
        ConfigurableApplicationContext ac =
                new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient networkClient = ac.getBean("networkClient", NetworkClient.class);
        ac.close(); // destroy() or close() 호출
    }

    @Configuration
    static class LifeCycleConfig {

        // 코드를 고칠 수 없는 외부 라이브러리에도 초기화, 종료 메서드를 설정 정보에서 적용할 수 있다.
        @Bean(initMethod = "init", destroyMethod = "close") // spring bean이 spring 코드에 의존하지 않도록 설계할 수 있음.
        public NetworkClient networkClient() {
            // url을 세팅하기 전에 connect, call을 하므로 url = null 으로 출력된다.
            NetworkClient networkClient = new NetworkClient(); // 빈 생성
            networkClient.setUrl("http://hello-spring.dev"); // 의존관계 주입 준비 완료
            // 의존관계 주입 완료 -> afterPropertiesSet() or init() 호출
            return networkClient;
        }
    }
}
