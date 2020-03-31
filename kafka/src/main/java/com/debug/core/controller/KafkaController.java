package com.debug.core.controller;

import com.debug.core.mq.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lyb
 * @since 2019-03-19
 */
@RestController
public class KafkaController {

    @Autowired
    private KafkaProducer producer;

    @RequestMapping(value = "/kafka/send", produces = "application/json;charset=UTF-8")
    public String send(String message) {
        producer.send(message);
        return "success";
    }
}
