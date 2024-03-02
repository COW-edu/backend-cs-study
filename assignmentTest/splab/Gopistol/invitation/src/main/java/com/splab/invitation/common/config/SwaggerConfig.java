package com.splab.invitation.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI swaggerApi() {
    return new OpenAPI()
        .components(new Components())
        .info(
            new Info()
                .title("Splab API Documentation")
                .description("Splab 구현 과제의 API 명세서입니다.")
                .version("0.0.1")
        );
  }
}
