package com.demo.microservices

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinApiDemoApplication

fun main(args: Array<String>) {
	runApplication<KotlinApiDemoApplication>(*args)
}
