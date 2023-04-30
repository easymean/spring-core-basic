package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

// 할인율을 적용
@Component
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy{

    private int rateDiscount = 10;

    @Override
    public int discount(Member member, int itemPrice) {
        if(member.getGrade() == Grade.VIP){
            return itemPrice*rateDiscount/100;
        }else {
            return 0;
        }
    }
}
