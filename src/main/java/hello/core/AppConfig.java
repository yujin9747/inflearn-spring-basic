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
@Configuration // @Configuration을 붙이지 않으면 스프링 컨테이너에는 잘 등록이 되나, 싱글톤이 깨진다(CGLIB 자식 클래스를 사용하지 않기 때문).
public class AppConfig { // 자바를 이용하는건 factoryMethod를 통해서 등록하는 방식, xml은 해당하지 않는다.
    // @Bean : spring container에 빈을 등록한다

    // 자바 코드 그대로라면 memberRepository는 총 3번 출력되어야 한다. -> @Configuration을 붙이지 않아서 CGLIB를 사용하지 않는 경우
    // memberRepository가 총 3번 생성된다.(싱글톤 깨짐)
    // AppConfig.memberService
    // AppConfig.memberRepository
    // AppConfig.memberRepository
    // AppConfig.orderService
    // AppConfig.memberRepository
    // 하지만 각각 한 번씩만 출력된다. -> @Configuration을 붙이면 싱글톤을 보장해준다.

    // @Autowired를 통해 싱글톤을 유지할 수도 있다.
    //    @Autowired MemberRepository memberRepository; // 생성자 주입
    @Bean
    public MemberService memberService() {
        System.out.println("AppConfig.memberService");
        return new MemberServiceImpl(memberRepository()); // 생성자 주입
    }

    @Bean
    public OrderService orderService() {
        System.out.println("AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
//        return null; // 필드 주입 설명하기 위해 위의 코드 주석 처리 함.
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
