package com.debug.demo;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EncryptorTests {

    @Autowired
    private StringEncryptor encryptor;

    @Test
    void contextLoads() {
        System.out.println(encryptor.encrypt("JustDoIT555~~~"));
    }

}
