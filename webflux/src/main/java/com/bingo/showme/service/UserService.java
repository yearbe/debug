package com.bingo.showme.service;

import com.bingo.showme.entity.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
	Flux<User> list();
	Flux<User> getByIds(Flux<Long> ids);
	Mono<User> getById(Long id);
	Mono<User> createOrUpdate(User user);
	Mono<User> deleteById(Long id);
}
