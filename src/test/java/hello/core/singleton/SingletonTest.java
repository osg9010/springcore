package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void purContainer(){
        AppConfig appConfig = new AppConfig();

        //LINE:: 1. 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        //LINE:: 2. 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        //LINE:: 참조값이 다른걸 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);
//        Assertions.assertThat(memberService1).isEqualTo(memberService2);
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);

    }

    @Test
    @DisplayName("싱글톤 생성 테스트")
    void singletonServiceeTest(){
        SingletonService instance1 = SingletonService.getInstance();
        SingletonService instance2 = SingletonService.getInstance();

        System.out.println(instance1);
        System.out.println(instance2);

        Assertions.assertThat(instance1).isEqualTo(instance2);
        Assertions.assertThat(instance1).isSameAs(instance2);
    }


    @Test
    @DisplayName("싱글톤 컨테이너와 싱글톤")
    void singletonContainer(){
//        AppConfig appConfig = new AppConfig();

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        //LINE:: 1. 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService1 = ac.getBean(MemberService.class);

        //LINE:: 2. 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService2 = ac.getBean(MemberService.class);

        //LINE:: 참조값이 다른걸 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        Assertions.assertThat(memberService1).isEqualTo(memberService2);
        Assertions.assertThat(memberService1).isSameAs(memberService2);

    }
}

