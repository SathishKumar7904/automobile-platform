package com.automobile.customer.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customerServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Automobile Platform - Customer Service API")
                        .version("1.0.0")
                        .description(
                                "REST API for managing customer information " +
                                "in the Automobile Digital Platform."
                        ));
    }
}