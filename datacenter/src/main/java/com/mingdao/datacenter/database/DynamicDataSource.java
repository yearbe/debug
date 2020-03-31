package com.mingdao.datacenter.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 动态数据源
 * @author Lyb
 * @since 2019-03-21
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Map<Object, Object> dynamicDataSources;

    @Autowired
    private DataSourceProperties dataSourceProperties;

    /**
     * 创建完Bean后初始化参数
     */
    @PostConstruct
    private void initDatabaseKeys() {
        DataSource defaultDataSource = (DataSource) getDefaultDataSourceConfig();
        // 设置默认数据源
        setDefaultTargetDataSource(defaultDataSource);
        // 缓存数据源
        addTargetDataSource(DatabaseConstant.DEFAULT_DATABASE, defaultDataSource);
        // 从数据库中读取数据库Key
        try {
            DataSourceOperator.cacheDatabaseKeyFromDefaultDatabase(getConnection());
        } catch (SQLException e) {
            logger.error("数据库连接异常", e);
        }
    }

    /**
     * 使用application数据源配置实例
     * @return
     */
    private Object getDefaultDataSourceConfig() {
        return DataSourceOperator.createDataSource(dataSourceProperties.getDriverClassName(),
                dataSourceProperties.getUrl(), dataSourceProperties.getUsername(), dataSourceProperties.getPassword());
    }

    /**
     * 获取当前数据源
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        String dataSourceName = DatabaseContextHolder.getDatabase();
        if (StringUtils.isBlank(dataSourceName)) {
            dataSourceName = DatabaseConstant.DEFAULT_DATABASE;
        } else {
            selectDataSource(dataSourceName);
        }
        return dataSourceName;
    }

    /**
     * 设置目标数据源
     * @param targetDataSources
     */
    @Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        dynamicDataSources = targetDataSources;
        super.setTargetDataSources(dynamicDataSources);
        afterPropertiesSet();
    }

    /**
     * 增加数据源
     * @param key
     * @param dataSource
     */
    private void addTargetDataSource(String key, DataSource dataSource) {
        if (dynamicDataSources == null) {
            dynamicDataSources = new HashMap<>();
        }
        dynamicDataSources.put(key, dataSource);
        setTargetDataSources(dynamicDataSources);
    }

    /**
     * 选择数据源，若未初始化，则从数据库中查询出来实例化
     * @param dataSourceName
     */
    private void selectDataSource(String dataSourceName) {
        Object sid = DatabaseContextHolder.getDatabase();
        if (StringUtils.isEmpty(dataSourceName)
                || dataSourceName.trim().equals(DatabaseConstant.DEFAULT_DATABASE)) {
            // 默认配置数据源
            DatabaseContextHolder.setDatabase(DatabaseConstant.DEFAULT_DATABASE);
            return;
        }

        // 从缓存中读取数据源
        Object obj = this.dynamicDataSources.get(dataSourceName);
        if (obj != null && sid.equals(dataSourceName)) {
            return;
        }

        // 从数据库中读取并实例化数据源
        DataSource dataSource = getDataSource(dataSourceName);
        if (null != dataSource) {
            setDataSource(dataSourceName, dataSource);
            DatabaseKeyCache.addKey(dataSourceName);
        }
    }

    /**
     * 增加数据源，并设置为当前线程默认
     * @param dataSourceName
     * @param dataSource
     */
    private void setDataSource(String dataSourceName, DataSource dataSource) {
        addTargetDataSource(dataSourceName, dataSource);
        DatabaseContextHolder.setDatabase(dataSourceName);
    }

    /**
     * 从默认数据库中读取数据源配置
     * @param dataSourceName
     * @return
     */
    private DataSource getDataSource(String dataSourceName) {
        // 切换为默认数据库
        selectDataSource(DatabaseConstant.DEFAULT_DATABASE);
        determineCurrentLookupKey();

        DataSource dataSource = null;
        try {
            // 查询数据源信息
            dataSource = DataSourceOperator.queryDataSource(getConnection(), dataSourceName);
        } catch (SQLException e) {
            logger.error("数据库连接异常", e);
        }
        return dataSource;
    }

}
