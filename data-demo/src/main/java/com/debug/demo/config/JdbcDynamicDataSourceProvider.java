package com.debug.demo.config;
import java.util.Properties;

import com.baomidou.dynamic.datasource.provider.AbstractJdbcDataSourceProvider;
import com.baomidou.dynamic.datasource.provider.DynamicDataSourceProvider;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.hikari.HikariCpConfig;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lyb
 * @since 2020-01-19
 */
public class JdbcDynamicDataSourceProvider extends AbstractJdbcDataSourceProvider implements DynamicDataSourceProvider {
    private final Map<String, DataSourceProperty> configDb;

    public JdbcDynamicDataSourceProvider(String driverClassName, String url, String username, String password) {
        super(driverClassName, url, username, password);

        configDb = new HashMap<>();
        DataSourceProperty defaultDataSourceProperty = new DataSourceProperty();
        defaultDataSourceProperty.setUsername(username);
        defaultDataSourceProperty.setPassword(password);
        defaultDataSourceProperty.setUrl(url);
        defaultDataSourceProperty.setDriverClassName(driverClassName);
        // mybatis-plus多数据源需要配置默认primary数据库，默认配置名称为master
        configDb.put("master", defaultDataSourceProperty);
    }

    @Override
    protected Map<String, DataSourceProperty> executeStmt(Statement statement) throws SQLException {
        ResultSet rs = statement.executeQuery("select name, username, password, url, driver from db_config");
        while (rs.next()) {
            String name = rs.getString("name");
            String username = rs.getString("username");
            String password = rs.getString("password");
            String url = rs.getString("url");
            String driver = rs.getString("driver");
            DataSourceProperty property = new DataSourceProperty();
            property.setUsername(username);
            property.setPassword(password);
            property.setUrl(url);
            property.setDriverClassName(driver);
            property.setHikari(initHikariCpConfig(name, username, password, url, driver));
            configDb.put(name, property);
        }
        DynamicDataSourceKeys.addKeys(configDb.keySet());
        return configDb;
    }

    private HikariCpConfig initHikariCpConfig(String name, String username, String password, String url, String driver) {
        HikariCpConfig hikariCpConfig = new HikariCpConfig();
        hikariCpConfig.setUsername(username);
        hikariCpConfig.setPassword(password);
        hikariCpConfig.setDriverClassName(driver);
        hikariCpConfig.setJdbcUrl(url);
        hikariCpConfig.setPoolName(name + "_pool");
        hikariCpConfig.setMinIdle(1);
        hikariCpConfig.setMaxPoolSize(100);
        hikariCpConfig.setIdleTimeout(60000L);
        hikariCpConfig.setMaxLifetime(1800000L);
        hikariCpConfig.setConnectionTimeout(60000L);
        hikariCpConfig.setValidationTimeout(60000L);
        hikariCpConfig.setConnectionInitSql("select 1");
        hikariCpConfig.setConnectionTestQuery("select 1");

        return hikariCpConfig;
    }
}
