package com.debug.demo.config;

import com.baomidou.dynamic.datasource.provider.DynamicDataSourceProvider;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author Lyb
 * @since 2020-01-20
 */
@Component
public class DynamicDataSourceConfig {

    @Autowired
    private DefaultDataSourceProperties defaultDataSourceProperties;
    @Autowired
    private StringEncryptor stringEncryptor;

    @Bean
    public DynamicDataSourceProvider dynamicDataSourceProvider() {
        return new JdbcDynamicDataSourceProvider(
                defaultDataSourceProperties.getDriverClassName(),
                defaultDataSourceProperties.getUrl(),
                defaultDataSourceProperties.getUsername(),
                stringEncryptor.decrypt(defaultDataSourceProperties.getPassword()));
    }
}
