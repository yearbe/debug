package com.bingo.showme.controller;

import java.time.Duration;

import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.netty.util.internal.ThreadLocalRandom;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuples;

@RestController
@RequestMapping("/sse")
public class SseController {

	@GetMapping("/show")
	public Flux<ServerSentEvent<Integer>> random() {
		return Flux.interval(Duration.ofSeconds(1))
				.map(seq -> Tuples.of(seq, ThreadLocalRandom.current().nextInt()))
				.map(data -> ServerSentEvent.<Integer>builder().event("random").id(Long.toString(data.getT1())).data(data.getT2()).build());
	}
}
