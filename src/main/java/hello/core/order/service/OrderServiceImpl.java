package hello.core.order.service;

import hello.core.discount.DiscountPolicy;
import hello.core.member.domain.Member;
import hello.core.member.repository.MemberRepository;
import hello.core.order.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// BeanDefinitionStoreException
// @Component("service")

// @Component("orderService2") // 빈 이름을 지정할 수 있음
@Component // 빈 이름을 지정하지 않으면 클래스 이름을 빈 이름으로 사용한다. (orderServiceImpl)
public class OrderServiceImpl implements OrderService {

    /**
     * 추상화에만 의존하는 경우 final : null 허용하지 않는 경우. 생성시 초기화해야 함 application과 관계 없는 테스트 코드 내에서 사용하는 경우 필드 주입
     * 방식 사용해도 괜찮다.
     */
    //    private final MemberRepository memberRepository;
    //    private final DiscountPolicy discountPolicy; // 외부에서  OrderServiceImpl에 DiscountPolicy 구현체를
    // 주입해줘야 함

    /** setter로 DI하는 경우 */
    //    private MemberRepository memberRepository;
    //    private DiscountPolicy discountPolicy; // 외부에서  OrderServiceImpl에 DiscountPolicy 구현체를 주입해줘야
    // 함

    /**
     * DI: 필드 주입 방법 권장 X : Field injection is not recommended 라는 메세지를 남긴다. 스프링 컨테이너가 아닌 순수한 자바로 테스트 하기
     * 어려움
     */
    @Autowired private MemberRepository memberRepository;

    @Autowired private DiscountPolicy discountPolicy;
    /**
     * DI: setter 주입 선택적 의존관계 주입. @Autowired(requeired = false)로 설정하면 의존관계가 없으면 메서드 자체가 호출되지 않는다. 변경
     * 가능성 있는 의존관계에 사용.
     */
    //    @Autowired
    //    public void setMemberRepository(MemberRepository memberRepository)
    //    {
    //        System.out.println("memberRepository = " + memberRepository);
    //        this.memberRepository = memberRepository;
    //    }
    //
    //    @Autowired
    //    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
    //        System.out.println("discountPolicy = " + discountPolicy);
    //        this.discountPolicy = discountPolicy;
    //    }

    /** 구현체에 의존하는 경우 */
    //    private DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //    private DiscountPolicy discountPolicy = new RateDiscountPolicy(); // OCP, DIP 위반

    /** DI: 생성자 주입 방법 불변, 필수 의존관계에 사용 생성자 호출 시점에 딱 1번만 호출되는 것이 보장된다. 생성자가 딱 1개만 있으면 Autowired 생략 가능 */
    //    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        System.out.println("Constructor로 주입");
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

    /** DI: 일반 메서드 주입 일반적으로 잘 사용하지 않는다. */
    //    @Autowired
    //    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy){
    //        this.memberRepository = memberRepository;
    //        this.discountPolicy = discountPolicy;
    //    }

}
