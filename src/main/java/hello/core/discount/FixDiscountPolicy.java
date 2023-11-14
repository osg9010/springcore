package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("fixB") // LINE :: 명시적으로 지정할 수 있게 하는 어노테이션 primary 보다 우선권이 높다
public class FixDiscountPolicy implements DiscountPolicy {
    private int discountFixAmount = 1000; // 1000원할인

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
//            price = price - discountFixAmount;
            return discountFixAmount;
        }
        return 0;
    }
}
