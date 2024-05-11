package com.thbs.attendance.Config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class RestTemplateConfigIntegrationTest {

    @Autowired
    private RestTemplateConfig restTemplateConfig;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testRestTemplateConfiguration() {
        // Ensure that RestTemplateConfig bean is created
        assertNotNull(restTemplateConfig);

        // Send a test request using the RestTemplate bean
        ResponseEntity<String> response = testRestTemplate.getForEntity("/test", String.class);

        // Assert the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        // Add more assertions as needed
    }
}
