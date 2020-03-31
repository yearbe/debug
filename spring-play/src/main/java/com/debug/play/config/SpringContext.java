package com.debug.play.config;

import com.debug.play.service.TestService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author Lyb
 * @since 2020-02-20
 */
//@Configuration
public class SpringContext implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /*@PostConstruct
    public void test() {
        applicationContext.getBean(TestService.class).sayHello();
    }*/
}
