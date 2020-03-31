package com.example.demo;

import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class DemoApplicationTests {
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	private boolean isSuccess = false;

	@Test
	void contextLoads() {
		jmsTemplate.convertAndSend(new ActiveMQQueue("test_1"), "请注意，这是一次测试。");
		log.info("消息发送完成");
		while (!isSuccess) {}
	}

	@JmsListener(destination = "test_1")
	public void receive(String msg) {
		log.info("接收消息：{}", msg);
		isSuccess = true;
	}
}
