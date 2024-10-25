package com.rapidsecure.rapidsecure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("basicScheme", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("basic")))
                .addSecurityItem(new SecurityRequirement().addList("basicScheme"))
                .info(new Info()
                        .title("Spring Boot 3 API")
                        .version("1.0")
                        .description("Sample Spring Boot 3 project with Swagger/OpenAPI integration"))
                .servers(getServers()); // Añade la lista de servidores
    }

    private List<Server> getServers() {
        return List.of(
                new Server().url("https://rapidsecure-backend.duckdns.org")
                //new Server().url("http://localhost:8080")
        );
    }
}
