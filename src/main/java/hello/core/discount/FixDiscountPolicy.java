package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

// 할인 금액을 일괄 적용
@Component
@Primary
public class FixDiscountPolicy implements DiscountPolicy{

    private int discountFixAccount = 1000;

    @Override
    public int discount(Member member, int itemPrice) {
        if(member.getGrade() == Grade.VIP){ // enum은 == 비교
            return discountFixAccount;
        }
        else {
            return 0;
        }
    }
}
