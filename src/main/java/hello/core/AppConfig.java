package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.repository.MemberRepository;
import hello.core.member.repository.MemoryMemberRepository;
import hello.core.member.service.MemberService;
import hello.core.member.service.MemberServiceImpl;
import hello.core.order.service.OrderService;
import hello.core.order.service.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 구성 영역
// memberRepository() 가 여러번 호출되면서 singleton이 깨지는 것 처럼 보이지만, @Configuration을 붙이면 싱글톤을 보장해준다.
@Configuration
public class AppConfig { // 자바를 이용하는건 factoryMethod를 통해서 등록하는 방식, xml은 해당하지 않는다.
    // @Bean : spring container에 빈을 등록한다

    // 자바 코드 그대로라면 memberRepository는 총 3번 출력되어야 한다.
    // AppConfig.memberService
    // AppConfig.memberRepository
    // AppConfig.memberRepository
    // AppConfig.orderService
    // AppConfig.memberRepository
    // 하지만 각각 한 번씩만 출력된다. -> @Configuration을 붙이면 싱글톤을 보장해준다.
    @Bean
    public MemberService memberService() {
        System.out.println("AppConfig.memberService");
        return new MemberServiceImpl(memberRepository()); // 생성자 주입
    }

    @Bean
    public OrderService orderService() {
        System.out.println("AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    // 역할을 모두 드러내는 방식으로 메소드를 뽑아낸다
    @Bean
    public DiscountPolicy discountPolicy() {
        //        return new FixDiscountPolicy();
        return new RateDiscountPolicy(); // 구성영역에서만 코드를 수정하면 된다 -> 클라이언트 코드 수정 필요 없음.
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }
}
