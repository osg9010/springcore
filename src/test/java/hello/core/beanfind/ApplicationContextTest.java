package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String n : beanDefinitionNames){
            Object bean = ac.getBean(n);
            System.out.println("bean = " + n + " Object = " + bean);
        }
    }
    @Test
    @DisplayName(" 애플리케이션 빈 출력하기")
    void findAllApplicationBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String n : beanDefinitionNames){
            BeanDefinition beanDefinition = ac.getBeanDefinition(n);

        // LINE :: ROLE_APPLICATION : 직접 등록한 애플리케이션 빈
        // LINE :: ROLE_INFRASTRUCTURE : 스프링 내부에서 사용하는 빈\
        if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
            Object bean = ac.getBean(n);
            System.out.println("bean = " + n + " // Object = " + bean);
        }
        }
    }
}
