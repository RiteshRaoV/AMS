package com.thbs.attendance.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.thbs.attendance.DTO.AttendanceUpdateDTO;
import com.thbs.attendance.DTO.EmployeeAttendanceDTO;
import com.thbs.attendance.Entity.Attendance;
import com.thbs.attendance.Service.AttendanceService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class AttendanceHistoryController {


    @Autowired
    private AttendanceService attendanceService;

    @GetMapping("/attendance/batch/{batchID}/course/{courseId}/Date/{date}/type/{type}")
    public ResponseEntity <List<EmployeeAttendanceDTO>> updateAttendance(@PathVariable Long batchID,@PathVariable Long courseId,@PathVariable String date,@PathVariable String type) {
        
        // Pass the Attendance object to the service for processing
        return ResponseEntity.ok(attendanceService.getAttendanceDetails(batchID,courseId,date,type)) ;
    }
    
}
