package com.demo.microservices.service

import com.demo.microservices.model.User
import com.demo.microservices.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val repository: UserRepository) {

    fun findAll(): List<User> = repository.findAll()

    fun findById(id: Long): User =
        repository.findById(id).orElseThrow {
            RuntimeException("Usuário não encontrado com id $id")
        }

    fun save(user: User): User {
        val saved = repository.save(user)
        println(">>> Salvo: $saved")
        return saved
    }

    fun delete(id: Long) {
        repository.deleteById(id)
    }
}
