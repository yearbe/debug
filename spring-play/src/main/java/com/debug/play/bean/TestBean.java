package com.debug.play.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author Lyb
 * @since 2020-02-20
 */
public class TestBean implements InitializingBean, DisposableBean {
    public void say(String msg) {
        System.out.println("You say: " + msg);
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("==> Test bean destroy.");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("==> Test bean after properties set.");
    }

    public void init() {
        System.out.println("==> Test bean init.");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("==> Test bean post construct.");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("==> Test bean pre destroy.");
    }
}
