package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "mqtt")
@PropertySource("classpath:application.properties")
public class MqttConfiguration {
	private String host;
	private String clientId;
	private String userName;
	private String password;
	private String topic;
	private int timeout;
	private int keepalive;
}
