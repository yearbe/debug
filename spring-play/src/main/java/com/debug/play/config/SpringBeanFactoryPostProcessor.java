package com.debug.play.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Configuration;

/**
 * @author Lyb
 * @since 2020-02-20
 */
@Configuration
public class SpringBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        // 在初始化bean实例之前修改BeanDefinition
        BeanDefinition beanDefinition = configurableListableBeanFactory.getBeanDefinition("sayToMe");
        beanDefinition.setInitMethodName("init");
    }
}
