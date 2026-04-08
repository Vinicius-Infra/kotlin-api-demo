package com.demo.microservices.service

import com.demo.microservices.model.User
import com.demo.microservices.repository.UserRepository
import com.demo.microservices.exception.UserNotFoundException
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.lang.Exception

@Service
class UserService(private val repository: UserRepository) {

    private val auditApiUrl = "http://localhost:8082/audit/log"

    fun findAll(): List<User> = repository.findAll()

    fun findById(id: Long): User =
        repository.findById(id).orElseThrow {
            UserNotFoundException("User not found with id $id")
        }

    fun save(user: User): User {
        // Define a operação antes de salvar, pois após o save o ID deixará de ser null
        val operation = if (user.id == null) "CREATE" else "UPDATE"

        // 1. Persistência local (Banco na 5434)
        val saved = repository.save(user)
        println(">>> local save: $saved")

        // 2. Integração com Auditoria (API Java na 8082)
        sendToAudit(saved, operation)

        return saved
    }

    fun delete(id: Long) {
        val userToDelete = findById(id) // Buscamos para ter os dados no log
        repository.deleteById(id)
        sendToAudit(userToDelete, "DELETE")
    }

    // Método auxiliar para não sujar o código principal
    private fun sendToAudit(user: User, operation: String) {
        try {
            val restTemplate = RestTemplate()
            val payload = mapOf(
                "userId" to user.id,
                "userName" to user.name,
                "operation" to operation
            )
            restTemplate.postForObject(auditApiUrl, payload, Map::class.java)
            println(">>> Auditoria: Evento [$operation] enviado para a API Java.")
        } catch (e: Exception) {
            println(">>> Erro Auditoria: Não foi possível comunicar com a API Java. Detalhe: ${e.message}")
        }
    }
}