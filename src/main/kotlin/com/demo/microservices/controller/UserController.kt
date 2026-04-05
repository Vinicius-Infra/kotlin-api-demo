package com.demo.microservices.controller

import com.demo.microservices.model.User
import com.demo.microservices.service.UserService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(private val service: UserService) {

    @GetMapping
    fun getUsers(): List<User> = service.findAll()

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): User = service.findById(id)

    @PostMapping(produces = ["application/json"])
    fun createUser(@Valid @RequestBody user: User): User = service.save(user)

    @PutMapping("/{id}")
    fun updateUser(
        @Valid @PathVariable id: Long,
        @RequestBody userDetails: User
    ): User {
        val user = service.findById(id)
        val updated = user.copy(
            name = userDetails.name,
            email = userDetails.email
        )
        return service.save(updated)
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long) {
        service.delete(id)
    }
}
