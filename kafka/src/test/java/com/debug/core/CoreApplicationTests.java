package com.debug.core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.UUID;

import com.debug.core.mq.KafkaProducer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CoreApplicationTests {

    @Autowired
    private KafkaProducer producer;

    @Test
    public void test() {
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            BufferedReaderProcessor brp = BufferedReader::readLine;
            String msg = brp.process(br);
            System.out.println(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSend() {
        for (int i = 0; i < 10; i++) {
            producer.send(UUID.randomUUID().toString());
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
