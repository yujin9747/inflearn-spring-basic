package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.repository.MemberRepository;
import hello.core.member.service.MemberServiceImpl;
import hello.core.order.service.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberService -> memberRepository1 = " + memberRepository1);
        System.out.println("orderService -> memberRepository2 = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        // memberRepository 인스턴스는 모두 같은 인스턴스가 공유되어 사용된다.
        Assertions.assertThat(memberRepository1).isSameAs(memberRepository);
        Assertions.assertThat(memberRepository2).isSameAs(memberRepository);
    }

    @Test
    void configurationDeep() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        // bean.getClass() = class hello.core.AppConfig$$SpringCGLIB$$0
        // 스프링이 AppConfig를 상속받는 AppConfig@CGLIB 라는 클래스를 만들고, 그 클래스를 스프링 빈으로 등록한다.
        System.out.println("bean.getClass() = " + bean.getClass());
        // @Configuration을 빼면 bean.getClass() = class hello.core.AppConfig 이 출력된다.
        // CGLIB 예시 코드
//        @Bean
//        public MemberRepository memberRepository(){
//            if(memberRepository가 이미 스프링 컨테이너에 있으면?){
//
//            }
//            else { // 스프링 컨테이너에 없으면?
//                기존 로직(AppConfig.class 내의 memberRepository())을 호출해서 MemoryMemberRepository를 생성하고 스프링 빈에 등록한다.
//                return 반환.
//            }
//        }
    }
}
