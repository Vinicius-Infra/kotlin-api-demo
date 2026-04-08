package com.demo.microservices.controller

import com.demo.microservices.model.User
import com.demo.microservices.service.UserService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI


@RestController
@RequestMapping("/users")
class UserController(private val service: UserService) {

    @GetMapping(produces = ["application/json"])
    fun getUsers(): ResponseEntity<List<User>> {
        val users = service.findAll()
        return if (users.isEmpty()) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.ok(users)
        }
    }

    @GetMapping("/{id}", produces = ["application/json"])
    fun getUserById(@PathVariable id: Long): ResponseEntity<User> {
    val user = service.findById(id) // Se não achar, o código para aqui e vai pro ExceptionHandler
    return ResponseEntity.ok(user)
}


    @PostMapping(produces = ["application/json"])
    fun createUser(@Valid @RequestBody user: User): ResponseEntity<User> {
    val saved = service.save(user)
    val uri = URI.create("/users/${saved.id}")
    return ResponseEntity.created(uri).body(saved)
}

    @PutMapping("/{id}")
    fun updateUser(
    @PathVariable id: Long,
    @Valid @RequestBody userDetails: User
): ResponseEntity<User> {
    val user = service.findById(id)
    val updated = user.copy(
        name = userDetails.name,
        email = userDetails.email
    )
    return ResponseEntity.ok(service.save(updated))
}

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long) {
        service.delete(id)
    }
}
