package com.debug.sqlite.database;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;

/**
 * @author Lyb
 * @since 2019-03-21
 */
@Repository
public class DatabaseConfig {
    /**
     * 创建数据源
     * @return
     */
    @Primary
    @Bean
    public DynamicDataSource dataSource() {
        return new DynamicDataSource();
    }


    /**
     * mybatis SqlSession 根据数据源创建
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
