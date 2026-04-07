package com.demo.microservices.controller

import com.demo.microservices.model.User
import com.demo.microservices.repository.UserRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertTrue
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerIntegrationTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var repository: UserRepository

    @BeforeEach
    fun setup() {
        repository.deleteAll()
    }

    @Test
    fun `POST deve criar usuario`() {
        val json = """{"name":"Joao","email":"joao@example.com"}"""

        mockMvc.perform(post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.name").value("Joao"))
            .andExpect(jsonPath("$.email").value("joao@example.com"))
    }

    @Test
    fun `GET deve listar usuarios`() {
        repository.save(User(name = "Maria", email = "maria@example.com"))

        mockMvc.perform(get("/users"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$[0].name").value("Maria"))
    }

    @Test
    fun `GET deve retornar usuario por id`() {
        val saved = repository.save(User(name = "Carlos", email = "carlos@example.com"))

        mockMvc.perform(get("/users/${saved.id}"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.name").value("Carlos"))
    }

    @Test
    fun `PUT deve atualizar usuario`() {
        val saved = repository.save(User(name = "Ana", email = "ana@example.com"))
        val json = """{"name":"Ana Paula","email":"ana.paula@example.com"}"""

        mockMvc.perform(put("/users/${saved.id}")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.name").value("Ana Paula"))
    }

    @Test
    fun `DELETE deve remover usuario`() {
        val saved = repository.save(User(name = "Pedro", email = "pedro@example.com"))

        mockMvc.perform(delete("/users/${saved.id}"))
            .andExpect(status().isOk)

        assertTrue(repository.findById(saved.id!!).isEmpty)
    }
}
