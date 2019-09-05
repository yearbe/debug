package com.debug.core.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author Lyb
 * @since 2019-03-18
 */
@Component
public class KafkaConsumer {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @KafkaListener(topics = {"ews.queue"})
    public void receive(String message) {
        logger.info("接收MQ消息：{}", message);
    }
}
