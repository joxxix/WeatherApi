package com.hackerrank.weather.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SpringFoxConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hackerrank"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiDetails());

    }

    private ApiInfo apiDetails(){
        return new ApiInfo("Weather API",
                "This application is used to train my self to Spring Boot and API handling",
                "Beta 0.1",
                "Free to use",
                new Contact("Nikolay", "https://github.com/joxxix","joxxix"),
                "API License",
                "",
                Collections.emptyList());
    }
}
