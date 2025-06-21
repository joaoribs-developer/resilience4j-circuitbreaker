package com.example.consumer

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Mono

@SpringBootApplication
class ConsumerApplication

fun main(args: Array<String>) {
    runApplication<ConsumerApplication>(*args)
}

@RestController
class SampleController {
    @GetMapping("/to-read")
    @CircuitBreaker(name = "readCircuitBreaker", fallbackMethod = "readFallback")
    fun read(): Mono<String> {
        return WebClient
			.builder()
			.baseUrl("http://localhost:8090/read")
			.build()
			.get()
			.retrieve()
			.bodyToMono<String>()
    }

    fun readFallback(e: Throwable) =
        Mono.just(
            """
		Spring Cloud  - Circuit Breaker Implementation with Resilience4J by Joao.
		Circuit Breaker Fallback method
		""".trimIndent()
        )
}
