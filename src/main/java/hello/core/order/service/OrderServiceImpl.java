package hello.core.order.service;

import hello.core.discount.DiscountPolicy;
import hello.core.member.domain.Member;
import hello.core.member.repository.MemberRepository;
import hello.core.order.domain.Order;

public class OrderServiceImpl implements OrderService {

    // memberRepository, discountPolicy 모두 추상화에만 의존함.
    private final MemberRepository memberRepository;
    //    private DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //    private DiscountPolicy discountPolicy = new RateDiscountPolicy(); // OCP, DIP 위반
    private final DiscountPolicy
            discountPolicy; // 외부에서  OrderServiceImpl에 DiscountPolicy 구현체를 주입해줘야 함

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
