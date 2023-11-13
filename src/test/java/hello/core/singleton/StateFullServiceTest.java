package hello.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StateFullServiceTest {

    @Test
    void statefulServiceSingleton(){

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StateFullService bean1 = ac.getBean(StateFullService.class);
        StateFullService bean2 = ac.getBean(StateFullService.class);

        //LINE:: A 사용자가 10000원 주문
        bean1.order("userA",10000);
        //LINE:: B 사용자가 20000원 주문
        bean2.order("userB",20000);

        int price = bean1.getPrice();

        System.out.println("price = " + price);

    }


    static class TestConfig{
        @Bean
        public StateFullService stateFullService(){
            return new StateFullService();
        }
    }
}