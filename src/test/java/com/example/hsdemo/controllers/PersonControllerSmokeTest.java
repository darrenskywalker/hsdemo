package com.example.hsdemo.controllers;

import com.example.testing.SpringConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@SpringBootTest(webEnvironment = DEFINED_PORT, classes = SpringConfig.class)
public class PersonControllerSmokeTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testSmokeTestController() {
        ResponseEntity<String> responseEntity = testRestTemplate.withBasicAuth("admin", "admin").getForEntity(
                "http://localhost:8080/v1/get-public-user-token", String.class);
        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
    }
}
