package com.example.testing;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.example.hsdemo"})
public class SpringConfig {

    @Bean
    TestRestTemplate restTemplate() {
        return new TestRestTemplate();
    }

    @Bean
    ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
