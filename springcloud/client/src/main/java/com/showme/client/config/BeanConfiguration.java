package com.showme.client.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Lyb
 * @since 2019-09-26 15:43
 */
@Configuration
public class BeanConfiguration {

    /**
     * LoadBalanced负载均衡配置(配置了该选项后，需要使用spring.application.name作为host请求服务)
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
