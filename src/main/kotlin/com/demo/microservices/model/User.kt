package com.demo.microservices.model

import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

@Entity
@Table(name = "users")
data class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @field:NotBlank(message = "The name is mandatory")
    val name: String,

    @field:Email(message = "The e-mail address must be valid")
    @field:NotBlank(message = "Email é obrigatório")
    val email: String
)
