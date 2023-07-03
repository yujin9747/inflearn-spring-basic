package hello.core.discount;

import hello.core.member.domain.Member;

public interface DiscountPolicy {

    /**
     * @return 할인 대상 급액
     */
    int discount(Member member, int price);

}
