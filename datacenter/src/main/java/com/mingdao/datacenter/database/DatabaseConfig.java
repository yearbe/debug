package com.mingdao.datacenter.database;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * 数据库
 * @author Lyb
 * @since 2019-03-21
 */
@Configuration
public class DatabaseConfig {

    /**
     * 创建动态切换数据源对象
     * @return
     */
    @Primary
    @Bean
    DynamicDataSource dataSource() {
        return new DynamicDataSource();
    }

    /**
     * mybatis SqlSession
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Primary
    @Bean
    public SqlSessionFactory sqlSessionFactory(DynamicDataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        return factoryBean.getObject();
    }

    /**
     * 事务管理器
     * @param dataSource
     * @return
     */
    @Primary
    @Bean
    public DataSourceTransactionManager transactionManager(DynamicDataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
