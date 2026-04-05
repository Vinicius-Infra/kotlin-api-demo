package com.demo.microservices.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    @GetMapping("/hello")
    fun hello(): String = "Bem-vindo ao Kotlin Microservices Demo!"
}
