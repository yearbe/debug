package com.example.demo;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.assertj.core.util.DateUtil;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.config.MqttConfiguration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class MqttTest {
	
	@Autowired
	private MqttConfiguration mqttConfiguration;
	
	private boolean isSuccess = false;

	@Test
	void test() {
		try {
			log.info("Mqtt connection: {}", mqttConfiguration.toString());
			MqttClient mqttClient = getMqttClient();
			mqttClient.subscribe(mqttConfiguration.getTopic());
			
			String date = DateUtil.formatAsDatetime(new Date());
			MqttMessage message = new MqttMessage(("这是一个测试" + date).getBytes());
	        MqttTopic mTopic = mqttClient.getTopic(mqttConfiguration.getTopic());
	        MqttDeliveryToken token = mTopic.publish(message);
	        token.waitForCompletion();
	        log.info("消息发送成功");
	        
	        while(!isSuccess) {}
	        TimeUnit.SECONDS.sleep(1L);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private MqttClient getMqttClient() throws MqttException {
		MqttClient mqttClient = new MqttClient(mqttConfiguration.getHost(), mqttConfiguration.getClientId());
		MqttConnectOptions options = new MqttConnectOptions();
		options.setCleanSession(false);
		options.setUserName(mqttConfiguration.getUserName());
		options.setPassword(mqttConfiguration.getPassword().toCharArray());
		options.setConnectionTimeout(mqttConfiguration.getTimeout());
		options.setKeepAliveInterval(mqttConfiguration.getKeepalive());
		mqttClient.setCallback(new MqttCallback() {
			
			@Override
			public void messageArrived(String topic, MqttMessage message) throws Exception {
				log.info("消息成功: {} - {}", topic, message.toString());
				isSuccess = true;
			}
			
			@Override
			public void deliveryComplete(IMqttDeliveryToken token) {
				log.info("deliveryComplete: {}", token.getResponse().toString());
			}
			
			@Override
			public void connectionLost(Throwable cause) {
				log.error("connectionLost: {}", cause.getMessage(), cause);
			}
		});
		mqttClient.connect(options);
		return mqttClient;
	}
}
