package com.bingo.showme.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;

import com.bingo.showme.entity.User;
import com.bingo.showme.service.UserService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
public class UserController {
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Rosource not found.")
	@ExceptionHandler(ResourceAccessException.class)
	public void notFound() {}
	
	@GetMapping("")
	public Flux<User> list() {
		return userService.list();
	}
	
	@GetMapping("/{id}")
	public Mono<User> getById(@PathVariable Long id) {
		return userService.getById(id);
	}
	
	@PostMapping("")
	public Mono<User> create(@RequestBody User user) {
		return userService.createOrUpdate(user);
	}
	
	@PutMapping("/{id}")
	public Mono<User> update(@PathVariable Long id, @RequestBody User user) {
		Objects.requireNonNull(user);
		Objects.requireNonNull(id);
		user.setId(id);
		return userService.createOrUpdate(user);
	}
	
	@DeleteMapping("/{id}")
	public Mono<User> delete(@PathVariable Long id) {
		return userService.deleteById(id);
	}
}
