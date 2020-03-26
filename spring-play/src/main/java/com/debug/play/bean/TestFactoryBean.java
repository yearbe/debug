package com.debug.play.bean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * @author Lyb
 * @since 2020-02-20
 */
@Component
public class TestFactoryBean implements FactoryBean<TestBean> {
    @Override
    public TestBean getObject() throws Exception {
        return new TestBean();
    }

    @Override
    public Class<?> getObjectType() {
        return TestBean.class;
    }
}
