package com.thbs.attendance.Service;

import com.thbs.attendance.DTO.BatchCourseDTO;
import com.thbs.attendance.DTO.BatchesDTO;
import com.thbs.attendance.DTO.EmployeeDTO;
import com.thbs.attendance.Exception.BatchIdNotFoundException;
import com.thbs.attendance.Exception.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import org.springframework.http.HttpMethod;
import org.springframework.core.ParameterizedTypeReference;



class BatchServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private BatchService batchService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCourses() {
        // Test data
        long batchId = 1L;
        BatchCourseDTO expectedBatchCourseDTO = new BatchCourseDTO(batchId, Arrays.asList());

        // Mock behavior
        String uri = "http://learning-plan-service/batch/" + batchId + "/courses";
        when(restTemplate.exchange(eq(uri), eq(HttpMethod.GET), isNull(), any(ParameterizedTypeReference.class)))
                .thenReturn(new ResponseEntity<>(expectedBatchCourseDTO, HttpStatus.OK));

        // Call method
        BatchCourseDTO result = batchService.getCourses(batchId);

        // Verify result
        assertNotNull(result);
        assertEquals(expectedBatchCourseDTO, result);
    }

    @Test
    void testGetBatches() {
        // Test data
        List<BatchesDTO> expectedBatchesDTO = Arrays.asList(new BatchesDTO(1L, "Batch1"), new BatchesDTO(2L, "Batch2"));

        // Mock behavior
        String uri = "http://batch-service/";
        when(restTemplate.getForObject(eq(uri), eq(BatchesDTO[].class))).thenReturn(expectedBatchesDTO.toArray(new BatchesDTO[0]));

        // Call method
        List<BatchesDTO> result = batchService.getBatches();

        // Verify result
        assertNotNull(result);
        assertEquals(expectedBatchesDTO.size(), result.size());
        assertEquals(expectedBatchesDTO, result);
    }

    @Test
    void testGetEmployeesByBatchId() {
        // Test data
        long batchId = 1L;
        EmployeeDTO[] employeeArray = {new EmployeeDTO(1L, "John", "Doe", "john@example.com", "Role", "BusinessUnit")};
        List<EmployeeDTO> expectedEmployees = Arrays.asList(employeeArray);

        // Mock behavior
        String uri = "http://batch-employees-service/" + batchId;
        when(restTemplate.getForObject(eq(uri), eq(EmployeeDTO[].class))).thenReturn(employeeArray);

        // Call method
        List<EmployeeDTO> result = batchService.getEmployeesByBatchId(batchId);

        // Verify result
        assertNotNull(result);
        assertEquals(expectedEmployees.size(), result.size());
        assertEquals(expectedEmployees, result);
    }

    @Test
    void testGetCourses_BatchNotFound() {
        // Test data
        long batchId = 1L;

        // Mock behavior
        String uri = "http://learning-plan-service/batch/" + batchId + "/courses";
        when(restTemplate.exchange(eq(uri), eq(HttpMethod.GET), isNull(), any(ParameterizedTypeReference.class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));

        // Verify behavior
        assertThrows(BatchIdNotFoundException.class, () -> batchService.getCourses(batchId));
    }

    @Test
    void testGetEmployeesByBatchId_BatchNotFound() {
        // Test data
        long batchId = 1L;

        // Mock behavior
        String uri = "http://batch-employees-service/" + batchId;
        when(restTemplate.getForObject(eq(uri), eq(EmployeeDTO[].class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));

        // Verify behavior
        assertThrows(BatchIdNotFoundException.class, () -> batchService.getEmployeesByBatchId(batchId));
    }

    @Test
    void testGetEmployeesByBatchId_NoEmployeesFound() {
        // Test data
        long batchId = 1L;

        // Mock behavior
        String uri = "http://batch-employees-service/" + batchId;
        when(restTemplate.getForObject(eq(uri), eq(EmployeeDTO[].class))).thenReturn(null);

        // Verify behavior
        assertThrows(UserNotFoundException.class, () -> batchService.getEmployeesByBatchId(batchId));
    }
}

