package com.debug.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Lyb
 * @since 2020-01-20
 */
@Data
@Component
@ConfigurationProperties(prefix = "default.datasource")
public class DefaultDataSourceProperties {
    private String url;
    private String username;
    private String password;
    private String driverClassName;
}
