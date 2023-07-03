package hello.core.discount;

import hello.core.member.domain.Grade;
import hello.core.member.domain.Member;

public class FixDiscountPolicy implements DiscountPolicy {
    private int discountFixAmmount = 1000; // 1000원 할인

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return discountFixAmmount;
        } else {
            return 0;
        }
    }
}
