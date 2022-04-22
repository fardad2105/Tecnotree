package com.fy.tecnotreedemo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiDocConfig {

    @Bean
    public GroupedOpenApi tecnotreeApi() {
        return GroupedOpenApi.builder()
                .group("tecnotree-apis")
                .pathsToMatch("/**")
                .pathsToExclude("/admin/**")
                .build();
    }


    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("Tecnotree Data Service")
                .description("List of APIs for the Tecnotree project")
                .version("1.0")
                .contact(new Contact()
                        .name("Fardad Yadegar")
                        .url("#")
                        .email("fardadyadegare@yahoo.com"))
                .license(new License()
                        .name("Tecnotree License")
                        .url("#"));
    }
}
