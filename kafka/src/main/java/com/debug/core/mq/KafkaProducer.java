package com.debug.core.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * @author Lyb
 * @since 2019-03-18
 */
@Component
public class KafkaProducer {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String message) {
        logger.info("发送MQ消息：{}", message);
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send("ews.queue", message);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                logger.error("发送MQ消息失败", ex);
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                logger.info("发送MQ消息成功:partition={}，offset={}", result.getRecordMetadata().partition(), result.getRecordMetadata().offset());
            }
        });
    }
}
