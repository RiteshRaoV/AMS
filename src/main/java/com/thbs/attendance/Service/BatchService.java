package com.thbs.attendance.Service;

import java.util.ArrayList;
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
import com.thbs.attendance.DTO.CourseDTO;
import com.thbs.attendance.DTO.Courses;
import com.thbs.attendance.DTO.EmployeeDTO;
import com.thbs.attendance.DTO.LearningPlanDTO;
import com.thbs.attendance.DTO.PathDTO;

@Service
public class BatchService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${learninPlanService.uri}")
    private String learningPlanServiceUri;

    @Value("${batchService.uri}")
    private String batchServiceUri;

    public BatchCourseDTO getCourses(long batchId) {

        // Build the URI with the batchId path variable
        String uri = UriComponentsBuilder.fromUriString(learningPlanServiceUri)
                .buildAndExpand(batchId)
                .toUriString();

        // Make the GET request to the URI
        ResponseEntity<List<LearningPlanDTO>> response = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<LearningPlanDTO>>() {
                });

        BatchCourseDTO batchCourseDTO = new BatchCourseDTO();
        batchCourseDTO.setBatchId(batchId);

        List<LearningPlanDTO> learningPlanDTOs = response.getBody();
        if (learningPlanDTOs != null) {
            List<Courses> courses = new ArrayList<>();
            for (LearningPlanDTO learningPlanDTO : learningPlanDTOs) {
                for (PathDTO path : learningPlanDTO.getPath()) {
                    CourseDTO courseDTO = path.getCourse();
                    long courseId = courseDTO.getCourseId();
                    String courseName = courseDTO.getCourseName();
                    Courses course = new Courses(courseId, courseName);
                    courses.add(course);
                }
            }
            batchCourseDTO.setCourses(courses);
        }

        return batchCourseDTO;
    }

    public List<BatchesDTO> getBatches() {
        String uri = UriComponentsBuilder.fromUriString(batchServiceUri)
                .toUriString();

        ResponseEntity<List<BatchesDTO>> response = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<BatchesDTO>>() {
                });

        return response.getBody();
    }

    // public List<EmployeeDTO> getEmployeesByBatchId(long batchId) {
    //     String url = batchServiceUri + "/batch-id/employees/"+ batchId;
    //     EmployeeDTO[] employeeArray = restTemplate.getForObject(url, EmployeeDTO[].class);
    //     return Arrays.asList(employeeArray);
    // }

    public List<EmployeeDTO> getEmployeesByBatchId(long batchId) {
        String url = batchServiceUri + "/batch-details/employees/" + batchId;
        EmployeeDTO[] employeeArray = restTemplate.getForObject(url, EmployeeDTO[].class);
        return Arrays.asList(employeeArray);
    }
}
