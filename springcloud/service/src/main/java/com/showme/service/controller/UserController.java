package com.showme.service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lyb
 * @since 2019-09-26 15:04
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/hello")
    public String hello(String name) {
        return "Hello, " + name + ".";
    }
}
