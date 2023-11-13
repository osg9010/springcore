package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.order.Order;
import hello.core.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {
//        MemberService memberService = new MemberServiceImpl();
//        OrderService orderService = new OrderServiceImpl();

//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);


        Member member = new Member(1l,"osk", Grade.VIP);
        Member member2 = new Member(2l,"obs", Grade.BASIC);
        memberService.join(member);
        memberService.join(member2);

        Order order1 = orderService.createOrder(member.getId(), "item1", 20000);
        Order order2 = orderService.createOrder(member2.getId(), "item2", 20000);
        System.out.println(order1);
        System.out.println(order1.calculatePrice());
        System.out.println(order2);
        System.out.println(order2.calculatePrice());

    }
}
