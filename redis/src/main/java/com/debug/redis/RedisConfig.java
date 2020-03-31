package com.debug.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.*;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Lyb
 * @since 2019-04-11
 */
@Configuration
public class RedisConfig {

    @Autowired
    private RedisProperties redisProperties;

    /*@Bean
    public JedisCluster jedisCluster() {
        Set<HostAndPort> nodes = redisProperties.getCluster().getNodes().stream().map(node -> {
            String[] hp = node.split(":");
            return new HostAndPort(hp[0], Integer.parseInt(hp[1]));
        }).collect(Collectors.toSet());
        // 创建集群对象
        return new JedisCluster(nodes);
    }*/

    @Bean
    public JedisPool jedisPool() {
        return new JedisPool(redisProperties.getHost(), redisProperties.getPort());
    }

    @Bean
    public Jedis jedis() {
        return jedisPool().getResource();
    }

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }
}
