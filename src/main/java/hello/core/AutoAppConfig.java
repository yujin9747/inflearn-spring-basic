package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class) // AppConfig.class를 제외하기 위함.
)
// @ComponentScan은 @Component가 붙은 클래스를 스캔해서 빈으로 등록한다. @Bean을 사용할 필요가 없다.
public class AutoAppConfig {

}
