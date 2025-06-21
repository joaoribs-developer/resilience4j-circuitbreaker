package com.example.publisher

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@SpringBootApplication
class PublisherApplication

fun main(args: Array<String>) {
	runApplication<PublisherApplication>(*args)
}

@RestController
class SampleController{
	@GetMapping("/read")
	fun read() = Mono.just("Spring Cloud  - Circuit Breaker Implementation with Resilience4J by Joao.")
}
