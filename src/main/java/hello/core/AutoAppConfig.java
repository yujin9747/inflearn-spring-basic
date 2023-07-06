package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        // 아래 둘 중 하나를 지정하지 않아도, @ComponentScan이 붙은 설정 정보 클래스의 패키지가 시작 위치가 된다.
        // 권장하는 방법 : 패키지 위치를 지정하지 않고, 설정 정보 클래스의 위치를 프로젝트 최상단에 두는 것. 최근 스프링 부트도 이 방법을 기본적으로 제공한다.
        basePackages = "hello.core", // 탐색할 패키지의 시작 위치를 지정한다. 이 패키지를 포함해서 하위 패키지를 모두 탐색한다. 여러개 설정 가능
        basePackageClasses = AutoAppConfig.class, // 지정한 클래스의 패키지를 탐색 시작 위치로 지정한다. 이 클래스가 속한 패키지를 포함해서 하위 패키지를 모두 탐색한다. 여러개 설정 가능
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class) // AppConfig.class를 제외하기 위함.
)
// @ComponentScan은 @Component가 붙은 클래스를 스캔해서 빈으로 등록한다. @Bean을 사용할 필요가 없다.
public class AutoAppConfig {

}
