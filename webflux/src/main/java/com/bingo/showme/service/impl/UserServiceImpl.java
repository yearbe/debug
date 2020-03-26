package com.bingo.showme.service.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.bingo.showme.entity.User;
import com.bingo.showme.service.UserService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {

	private final AtomicLong atomicLong = new AtomicLong();
	private final Map<Long, User> data = new ConcurrentHashMap<>();
	
	@Override
	public Flux<User> list() {
		return Flux.fromIterable(data.values());
	}

	@Override
	public Flux<User> getByIds(Flux<Long> ids) {
		return ids.flatMap(id -> Mono.justOrEmpty(data.get(id)));
	}

	@Override
	public Mono<User> getById(Long id) {
		return Mono.justOrEmpty(data.get(id))
				.switchIfEmpty(Mono.error(new ResourceAccessException("User not found.")));
	}

	@Override
	public Mono<User> createOrUpdate(User user) {
		if (user.getId() == null) {
			user.setId(atomicLong.incrementAndGet());
		}
		data.put(user.getId(), user);
		return Mono.just(user);
	}

	@Override
	public Mono<User> deleteById(Long id) {
		return Mono.justOrEmpty(data.remove(id));
	}

}
