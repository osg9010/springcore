package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationTest {

    @Test
    void configurationTest(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl bean = ac.getBean(MemberServiceImpl.class);
        OrderServiceImpl bean1 = ac.getBean(OrderServiceImpl.class);

        MemberRepository bean2 = ac.getBean(MemberRepository.class);

        MemberRepository memberRepository = bean.getMemberRepository();
        MemberRepository memberRepository1 = bean1.getMemberRepository();
        System.out.println(memberRepository);
        System.out.println(memberRepository1);
        System.out.println(bean2);

        Assertions.assertThat(bean.getMemberRepository()).isSameAs(bean1.getMemberRepository());
        Assertions.assertThat(bean2).isSameAs(bean1.getMemberRepository());
    }

    @Test
    void configurationDeep(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);
        System.out.println(bean);
    }
}

