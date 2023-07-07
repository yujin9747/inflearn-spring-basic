package hello.core.order.service;

import hello.core.discount.DiscountPolicy;
import hello.core.member.domain.Member;
import hello.core.member.repository.MemberRepository;
import hello.core.order.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component // 빈 이름을 지정하지 않으면 클래스 이름을 빈 이름으로 사용한다. (orderServiceImpl)
@RequiredArgsConstructor // final이 붙은 필드를 모아서 생성자를 자동으로 만들어준다.
public class OrderServiceImpl implements OrderService {
    private final MemberRepository memberRepository;

    private final DiscountPolicy discountPolicy; // 외부에서  OrderServiceImpl에 DiscountPolicy 구현체를 주입해줘야 함

    /**
     * DI: 생성자 주입 방법 불변, 필수 의존관계에 사용 생성자 호출 시점에 딱 1번만 호출되는 것이 보장된다.
     * 생성자가 딱 1개만 있으면 Autowired 생략 가능
     *
     * @RequiredArgsConstructor를 사용하면 final이 붙은 필드를 모아서 생성자를 자동으로 만들어준다.
     */
    //    @Autowired
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        System.out.println("Constructor로 주입");
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }
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
