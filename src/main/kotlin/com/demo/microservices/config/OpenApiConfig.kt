package com.demo.microservices.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {

    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("Kotlin API Demo")
                    .version("1.0.0")
                    .description("Documentação da API de Microserviços de Usuários")
                    .contact(
                        Contact()
                            .name("Marcos Vinicius da Silva Barreto")
                            .email("vinicius.barreto994@gmail.com")
                    )
            )
    }
}