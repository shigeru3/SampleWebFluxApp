package com.example.samplewebfluxapp;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class SampleRestController {
	@Autowired
	PostRepository repository;

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

	@RequestMapping("/post")
	public Mono<Post> post() {
		Post post  = new Post(0, 0, "dummy", "dummy message");
		return Mono.just(post);
	}

	@RequestMapping("/post/{id}")
	public Mono<Post> post(@PathVariable int id) {
		Post post = repository.findById(id);
		return Mono.just(post);
	}

	@RequestMapping("/posts")
	public Flux<Object> posts() {
		List<Post> posts = repository.findAll();
		return Flux.fromArray(posts.toArray());
	}

	@PostConstruct
	public void init() {
		Post p1 = new Post(1, 1, "Hello", "Hello Flux");
		Post p2 = new Post(2, 2, "Sample", "This is sample");
		Post p3 = new Post(3, 3, "Hi", "This is post");
		repository.saveAndFlush(p1);
		repository.saveAndFlush(p2);
		repository.saveAndFlush(p3);
	}
}
