package hello.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication에 이미 @ComponentScan이 적용되어 있다. 따라서 hello.core 내의 모든 Component를 스캔한다.
@SpringBootApplication
public class InflearnSpringBasicApplication {

    public static void main(String[] args) {
        SpringApplication.run(InflearnSpringBasicApplication.class, args);
    }
}
