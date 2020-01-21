package com.debug.demo.service;

import com.debug.demo.entity.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Lyb
 * @since 2020-01-19
 */
public interface UserService {
    Mono<Void> save(User user);

    Mono<User> query(Long id);

    Flux<User> list();
}
