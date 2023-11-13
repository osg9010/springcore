package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy rateDiscountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP 는 10프로 할인이 되어야한다")
    void vip_o(){
        //given
        Member member = new Member(1L, "membervip", Grade.VIP);
        //when
        int discount = rateDiscountPolicy.discount(member,10000);
        //then
        assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("vip 가 아니면 10프로 할인이 되지 않는다.")
    void vip_x(){
        //given
        Member member = new Member(1L, "membervip", Grade.BASIC);
        //when
        int discount = rateDiscountPolicy.discount(member,10000);
        //then
        assertThat(discount).isEqualTo(0);
    }

}