package com.thbs.attendance.Config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class CorsConfigIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCorsConfiguration() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/test")) // Send a mock GET request
                .andExpect(MockMvcResultMatchers.status().isOk()) // Expect HTTP 200 OK
                .andExpect(MockMvcResultMatchers.header().string("Access-Control-Allow-Origin", "*")); // Expect CORS header
    }
}
