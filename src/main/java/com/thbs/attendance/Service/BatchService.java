package com.thbs.attendance.Service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.thbs.attendance.DTO.BatchCourseDTO;
import com.thbs.attendance.DTO.BatchesDTO;
import com.thbs.attendance.DTO.EmployeeDTO;

@Service
public class BatchService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${learninPlanService.uri}")
    private String learningPlanServiceUri;

    @Value("${batchService.uri}")
    private String batchServiceUri;

    @Value("${batchEmployees.uri}")
    private String batchEmployeesUri;

    public BatchCourseDTO getCourses(long batchId) {

        // Build the URI with the batchId path variable
        String uri = UriComponentsBuilder.fromUriString(learningPlanServiceUri)
        .buildAndExpand(batchId)
        .toUriString();

        // Make the GET request to the URI
        ResponseEntity<BatchCourseDTO> response = restTemplate.exchange(
        uri,
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<BatchCourseDTO>() {
        });

        BatchCourseDTO batchCourseDTO = response.getBody();

        return batchCourseDTO;
    }

    public List<BatchesDTO> getBatches() {
        String uri = UriComponentsBuilder.fromUriString(batchServiceUri).toUriString();
        BatchesDTO[] response = restTemplate.getForObject(uri, BatchesDTO[].class);
        return Arrays.asList(response);
    }

    public List<EmployeeDTO> getEmployeesByBatchId(long batchId) {
        String url = UriComponentsBuilder.fromUriString(batchEmployeesUri)
        .buildAndExpand(batchId)
        .toUriString();
        EmployeeDTO[] employeeArray = restTemplate.getForObject(url, EmployeeDTO[].class);
        return Arrays.asList(employeeArray);
    }
}
