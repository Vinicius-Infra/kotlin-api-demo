package com.demo.microservices.service

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class KafkaProducerService(private val kafkaTemplate: KafkaTemplate<String, String>) {

    fun sendLog(message: String) {
        val topic = "audit-log-topic"
        println("📡 Enviando para o Kafka: $message")
        kafkaTemplate.send(topic, message)
    }
}