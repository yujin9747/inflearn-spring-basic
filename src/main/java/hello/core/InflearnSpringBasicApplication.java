package hello.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * spring의 life cycle 1. 스프링 컨테이너 생성 2. 스프링 빈 등록 3. 스프링 빈 의존관계 설정 - 준비 : @Component, @Configuration
 * 4. 스프링 빈 의존관계 설정 - 완료 : @Autowired
 */
// @SpringBootApplication에 이미 @ComponentScan이 적용되어 있다. 따라서 hello.core 내의 모든 Component를 스캔한다.
@SpringBootApplication
public class InflearnSpringBasicApplication {

    public static void main(String[] args) {
        SpringApplication.run(InflearnSpringBasicApplication.class, args);
    }
}
