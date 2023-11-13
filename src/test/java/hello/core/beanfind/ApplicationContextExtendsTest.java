package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextExtendsTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);


    @Test
    @DisplayName("부모 타입으로 조회 시 자식이 둘 이상 이씅면 중복 오류가 발생한다.")
    void findBeanByParentsDuplicate(){
        assertThrows(NoUniqueBeanDefinitionException.class, () ->
                ac.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("자식이 둘 이상 있으면 빈 이름을 지정하면 된다.")
    void findBeanByParentsTypeBeanName(){
        DiscountPolicy discountPolicy1 = ac.getBean("DiscountPolicy1", DiscountPolicy.class);
        assertThat(discountPolicy1).isInstanceOf(FixDiscountPolicy.class);
    }

    // LINE :: 좋지 않은 방식
    @Test
    @DisplayName("자식이 둘 이상 있으면 특정 하위 타입으로 조회하면 된다.")
    void findBeanBySubType(){
        DiscountPolicy discountPolicy1 = ac.getBean(RateDiscountPolicy.class);
        assertThat(discountPolicy1).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기")
    void findAllBeanByParents(){
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        assertThat(beansOfType.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Object 클래스로 모두 조회하기")
    void findAllBeanByObjectClass(){
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        assertThat(beansOfType.size()).isEqualTo(16);
    }


    @Configuration
    static class TestConfig {

        @Bean
        public DiscountPolicy DiscountPolicy1() {
            return new FixDiscountPolicy();
        }

        @Bean
        public DiscountPolicy DiscountPolicy2() {
            return new RateDiscountPolicy();
        }
    }


}
