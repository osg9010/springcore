package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Primary // LINE :: 여러 타입의 같은 빈이 있을 경우 기본 주입되는 빈으로 등록
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {

        if(member.getGrade()== Grade.VIP){
            return  price*discountPercent/100;
        }
        return 0;
    }
}
