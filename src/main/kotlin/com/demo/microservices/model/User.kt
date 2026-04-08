package com.demo.microservices.model

import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import io.swagger.v3.oas.annotations.media.Schema

@Entity
@Table(name = "users")
@Schema(description = "Represents a user in the system")
data class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @field:Schema(accessMode = Schema.AccessMode.READ_ONLY) 
    val id: Long? = null,

    @field:NotBlank(message = "The name is mandatory")
    @field:Schema(example = "João Silva", description = "user's full name") 
    val name: String,

    @field:NotBlank(message = "The e-mail is mandatory") // Adicione esta linha
    @field:Email(message = "The e-mail address must be valid")
    @field:Schema(example = "joao.silva@example.com", description = "The user's e-mail address")
    val email: String
)