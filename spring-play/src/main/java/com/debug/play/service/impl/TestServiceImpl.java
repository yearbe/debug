package com.debug.play.service.impl;

import com.debug.play.service.TestService;
import org.springframework.stereotype.Service;

/**
 * @author Lyb
 * @since 2020-02-20
 */
@Service
public class TestServiceImpl implements TestService {
    @Override
    public void sayHello() {
        System.out.println("Spring say hello to you.");
    }
}
