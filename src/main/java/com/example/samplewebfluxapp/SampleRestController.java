package com.example.samplewebfluxapp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class SampleRestController {
	@RequestMapping("/")
	public String hello() {
		return "Hello Flux!";
	}

	@RequestMapping("/flux")
	public Mono<String> flux() {
		return Mono.just("Hello Flux (Mono)");
	}

	@RequestMapping("/flux2")
	public Flux<String> flux2() {
		return Flux.just("Hello Flux", "This is sample of Flux");
	}
}
