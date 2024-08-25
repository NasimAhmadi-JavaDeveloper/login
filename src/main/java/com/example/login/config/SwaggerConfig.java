package com.example.login.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;


@Configuration
public class SwaggerConfig implements WebMvcConfigurer {

    @Value("${base.url}")
    private String baseUrl;

    @Bean
    public OpenAPI apiInfo() {
        final String securitySchemeName = "bearerAuth";
        Server server = new Server();
        Server serverLocal = new Server();
        serverLocal.setUrl(baseUrl);
        server.setUrl(baseUrl);
        List<Server> servers = new ArrayList<>();
        servers.add(serverLocal);
        servers.add(server);
        return new OpenAPI()
                .servers(servers)
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components().addSecuritySchemes(securitySchemeName,
                        new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));
    }
}
