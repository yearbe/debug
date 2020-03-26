package com.debug.play.config;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author Lyb
 * @since 2020-02-20
 */
//@Configuration("testSpringBeanName")
public class SpringBean implements BeanNameAware {
    private String beanName;

    @Override
    public void setBeanName(String s) {
        this.beanName = s;
    }

    /*@PostConstruct
    private void test() {
        System.out.println(this.beanName);
    }*/
}
