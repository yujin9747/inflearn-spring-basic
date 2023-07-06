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
}
