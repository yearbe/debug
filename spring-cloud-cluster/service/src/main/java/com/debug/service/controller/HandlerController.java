package com.debug.service.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lyb
 * @since 2019-09-29 15:43
 */
@RestController
public class HandlerController {
    @Value("${eureka.instance.instance-id}")
    public String instanceId;

    @RequestMapping("/api")
    public String api(String param) {
        return instanceId + ": " + param;
    }
}
