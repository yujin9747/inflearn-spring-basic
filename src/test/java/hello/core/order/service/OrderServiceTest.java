package hello.core.order.service;

import hello.core.AppConfig;
import hello.core.member.domain.Grade;
import hello.core.member.domain.Member;
import hello.core.member.service.MemberService;
import hello.core.order.domain.Order;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderServiceTest {
    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void BeforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder() {
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

    // 필드 주입 방식을 사용할 때 발생하는 문제점 : 스프링 없이 순수 자바로 테스트 하기 어렵다.
    //    @Test
    //    void fieldInjectionTest() {
    //        OrderServiceImpl orderService = new OrderServiceImpl();
    //
    //        // DI를 위한 생성자가 추가적으로 필요함
    //
    //        orderService.createOrder(1L, "itemA", 10000);
    //    }
}
