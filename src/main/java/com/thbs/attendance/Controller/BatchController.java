package com.thbs.attendance.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class BatchController {
    @Autowired
    private RestTemplate restTemplate;

    
}
