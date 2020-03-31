package com.debug.sqlite.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 动态数据源
 *
 * @author Lyb
 * @since 2019-03-20
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    private Map<Object, Object> dynamicDataSources = new ConcurrentHashMap<>();

    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Override
    protected Object determineCurrentLookupKey() {
        String dataSourceName = DatabaseContextHolder.getDatabaseType();
        if (dataSourceName == null) {
            dataSourceName = DatabaseConstant.DEFAULT_DATABASE;
        } else {
            this.selectDataSource(dataSourceName);
        }
        return dataSourceName;
    }

    @Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        this.dynamicDataSources = targetDataSources;
        super.setTargetDataSources(this.dynamicDataSources);
        afterPropertiesSet();
    }

    private void addTargetDataSource(String key, DataSource dataSource) {
        this.dynamicDataSources.put(key, dataSource);
        this.setTargetDataSources(this.dynamicDataSources);
    }

    private void selectDataSource(String dataSourceName) {
        Object sid = DatabaseContextHolder.getDatabaseType();
        if (StringUtils.isEmpty(dataSourceName)
                || dataSourceName.trim().equals(DatabaseConstant.DEFAULT_DATABASE)) {
            DatabaseContextHolder.setDatabaseType(DatabaseConstant.DEFAULT_DATABASE);
            return;
        }
        Object obj = this.dynamicDataSources.get(dataSourceName);
        if (obj != null && sid.equals(dataSourceName)) {
            return;
        }

        DataSource dataSource = this.getDataSource(dataSourceName);
        if (null != dataSource) {
            this.setDataSource(dataSourceName, dataSource);
            DatabaseKey.addKey(dataSourceName);
        }
    }

    private void setDataSource(String dataSourceName, DataSource dataSource) {
        this.addTargetDataSource(dataSourceName, dataSource);
        DatabaseContextHolder.setDatabaseType(dataSourceName);
    }

    @PostConstruct
    private void initDatabaseKeys() {
        DataSource defaultDataSource = (DataSource) getDefaultDataSourceConfig();
        setDefaultTargetDataSource(defaultDataSource);
        addTargetDataSource(DatabaseConstant.DEFAULT_DATABASE, defaultDataSource);
        cacheDatabaseKeyFromDb();
    }

    private void cacheDatabaseKeyFromDb() {
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "SELECT name FROM database_config";

            PreparedStatement ps = conn.prepareStatement(querySql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                DatabaseKey.addKey(name);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            log.error("SQL异常", e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                log.error("SQL异常", e);
            }
        }
    }

    private Object getDefaultDataSourceConfig() {
        return createDataSource(dataSourceProperties.getDriverClassName(),
                dataSourceProperties.getUrl(), dataSourceProperties.getUsername(), dataSourceProperties.getPassword());
    }

    private DataSource getDataSource(String dataSourceName) {
        DataSource dataSource = null;
        this.selectDataSource(DatabaseConstant.DEFAULT_DATABASE);
        this.determineCurrentLookupKey();
        Connection conn = null;
        try {
            conn = this.getConnection();
            String querySql = "SELECT name,driver,url,username,password FROM database_config WHERE name = ?";

            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setString(1, dataSourceName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String driverClassName = rs.getString("driver");
                String url = rs.getString("url");
                String userName = rs.getString("username");
                String password = rs.getString("password");
                dataSource = this.createDataSource(
                        driverClassName, url, userName, password);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            log.error("SQL异常", e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                log.error("SQL异常", e);
            }
        }
        return dataSource;
    }

    /**
     * 创建数据源
     *
     * @param driverClassName
     * @param url
     * @param username
     * @param password
     * @return
     */
    private DataSource createDataSource(
            String driverClassName, String url, String username, String password) {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(driverClassName);
        config.setJdbcUrl(url);
        if (username != null && !username.isEmpty()) {
            config.setUsername(username);
        }
        if (password != null && !password.isEmpty()) {
            config.setPassword(password);
        }

        return new HikariDataSource(config);
    }
}
