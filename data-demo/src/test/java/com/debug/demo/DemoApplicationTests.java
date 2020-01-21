package com.debug.demo;

import com.debug.demo.entity.User;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//@SpringBootTest
class DemoApplicationTests {
    @Test
    void contextLoads() {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 1000; i++) {
            executorService.execute(() -> {
                WebClient webClient = WebClient.create("http://localhost:7878/user");
                Mono<User> user = webClient.get().uri("/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("token", "1")
                        .exchange()
                        .flatMap(res -> res.bodyToMono(User.class));
                System.out.println(user.block());
            });
        }
        while (true) {}
    }

}
