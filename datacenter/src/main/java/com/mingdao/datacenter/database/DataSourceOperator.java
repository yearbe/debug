package com.mingdao.datacenter.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Lyb
 * @since 2019-03-21
 */
public class DataSourceOperator {
    private static final Logger logger = LoggerFactory.getLogger(DataSourceOperator.class);

    /**
     * 查询数据源
     * @param conn
     * @param dataSourceName
     * @return
     */
    public static DataSource queryDataSource(Connection conn, String dataSourceName) {
        DataSource dataSource = null;
        try {
            String querySql = "SELECT name,driver,url,username,password FROM database_config WHERE name = ?";

            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setString(1, dataSourceName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String driverClassName = rs.getString("driver");
                String url = rs.getString("url");
                String userName = rs.getString("username");
                String password = rs.getString("password");
                dataSource = createDataSource(
                        driverClassName, url, userName, password);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            logger.error("数据库连接异常", e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                logger.error("数据库连接关闭异常", e);
            }
        }
        return dataSource;
    }

    /**
     * 将数据库中所有数据源Key缓存起来
     */
    public static void cacheDatabaseKeyFromDefaultDatabase(Connection conn) {
        try {
            String querySql = "SELECT name FROM database_config";

            PreparedStatement ps = conn.prepareStatement(querySql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                DatabaseKeyCache.addKey(name);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            logger.error("数据库连接异常", e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                logger.error("数据库连接关闭异常", e);
            }
        }
    }

    /**
     * 使用HikariDataSource实例化数据源
     * @param driverClassName
     * @param url
     * @param username
     * @param password
     * @return
     */
    public static DataSource createDataSource(
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
