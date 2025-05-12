package com.siscon.employee.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    public static final String EMPLOYEE_API = "Employee API";
    public static final String VERSION = "0.0.1";

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info().title(EMPLOYEE_API).version(VERSION));
    }
}
