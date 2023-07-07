package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient networkClient = ac.getBean("networkClient", NetworkClient.class);
        ac.close(); // destroy() 메서드 호출
    }

    @Configuration
    static class LifeCycleConfig {
        @Bean
        public NetworkClient networkClient() {
            // url을 세팅하기 전에 connect, call을 하므로 url = null 으로 출력된다.
            NetworkClient networkClient = new NetworkClient();   // 빈 생성
            networkClient.setUrl("http://hello-spring.dev"); // 의존관계 주입 준비 완료
            // 의존관계 주입 완료 -> afterPropertiesSet() 호출
            return networkClient;
        }
    }
}
