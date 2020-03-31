package com.debug.play;

import com.debug.play.bean.TestBean;
import com.debug.play.service.TestService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * https://docs.spring.io/spring/docs/5.2.3.RELEASE/spring-framework-reference/core.html#beans-value-annotations
 * @author Lyb
 * @since 2020-02-20
 */
@Configuration
@ComponentScan("com.debug.play")
public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        // 使spring context正常关闭
        context.registerShutdownHook();
//        TestBean testBean = context.getBean("sayToMe", TestBean.class);
//        testBean.say("I'm a test bean.");
//        TestBean factoryBean = (TestBean) context.getBean("testFactoryBean");
//        factoryBean.say("I'm a factory bean.");
//        TestService testService = context.getBean(TestService.class);
//        testService.sayHello();
    }

    @Bean("sayToMe")
    public TestBean testBean() {
        return new TestBean();
    }
}
