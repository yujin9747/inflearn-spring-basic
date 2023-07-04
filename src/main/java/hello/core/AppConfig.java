package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.repository.MemberRepository;
import hello.core.member.repository.MemoryMemberRepository;
import hello.core.member.service.MemberService;
import hello.core.member.service.MemberServiceImpl;
import hello.core.order.service.OrderService;
import hello.core.order.service.OrderServiceImpl;

// 구성 영역
public class AppConfig {
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository()); // 생성자 주입
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    // 역할을 모두 드러내는 방식으로 메소드를 뽑아낸다
    private DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();    // 구성영역에서만 코드를 수정하면 된다 -> 클라이언트 코드 수정 필요 없음.
    }

    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
