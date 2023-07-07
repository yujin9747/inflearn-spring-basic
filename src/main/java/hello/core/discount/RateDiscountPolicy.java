package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.domain.Grade;
import hello.core.member.domain.Member;
import org.springframework.stereotype.Component;

@Component
@MainDiscountPolicy
//@Primary // @Primary가 붙은 빈이 우선권을 가진다.
public class RateDiscountPolicy implements DiscountPolicy {
    private int discountPercent = 10; // 10% 할인

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
