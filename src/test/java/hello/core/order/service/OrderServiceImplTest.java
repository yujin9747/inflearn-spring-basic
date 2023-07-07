package hello.core.order.service;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.domain.Grade;
import hello.core.member.domain.Member;
import hello.core.member.repository.MemoryMemberRepository;
import hello.core.order.domain.Order;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceImplTest {

    @Test
    void createOrder() {
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "name", Grade.VIP));

        // null exception 발생 -> 순수 자바 코드로 테스트하기 어렵다. 의존관계를 확인하고 직접 넣어주기 어렵다
//        OrderServiceImpl orderService = new OrderServiceImpl();
        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());
        Order order = orderService.createOrder(1L, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
