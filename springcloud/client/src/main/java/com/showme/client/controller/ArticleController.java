package com.showme.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Lyb
 * @since 2019-09-26 15:55
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/callHello")
    public String callHello(String name) {
        if (name == null) {
            name = "article";
        }
        return new RestTemplate().getForObject("http://localhost:4444/user/hello?name=" + name, String.class);
    }

    @RequestMapping("/sayHello")
    public String sayHello(String name) {
        if (name == null) {
            name = "article";
        }
        // 负载均衡方式使用应用名作为host请求
        return restTemplate.getForObject("http://service/user/hello?name=" + name, String.class);
    }
}
