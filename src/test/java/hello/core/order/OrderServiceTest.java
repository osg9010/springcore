package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberService;
    OrderService orderService ;

    @BeforeEach
    void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder(){
        Long memberid = 1L;
        Member member = new Member(memberid, "member1", Grade.VIP);
        memberService.join(member);

        Order item1 = orderService.createOrder(memberid, "item1", 10000);
        Assertions.assertThat(item1.getDiscountPrice()).isEqualTo(1000);


    }
}
