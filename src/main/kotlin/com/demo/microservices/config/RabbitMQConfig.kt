package com.demo.microservices.config 

import org.springframework.amqp.core.Queue
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMQConfig {

    // Criamos uma fila chamada "audit_log_queue"
    @Bean
    fun auditQueue(): Queue {
        return Queue("audit_log_queue", true)
    }
}