package com.thbs.attendance.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.thbs.attendance.DTO.UpdateAttendanceDTO;
import com.thbs.attendance.Service.AttendanceService;

@RestController
public class AttendanceController {
    @Autowired
    private AttendanceService attendanceService;

    @GetMapping
    private ResponseEntity<?> updateAttendance(@RequestBody UpdateAttendanceDTO updateAttendance){
        return ResponseEntity.ok().build();
    }
}

// {
//     "batchId":"1",
//     "courseId":"1",
//     "type":"1stHalf",
//     "attendance":[
//         {
//             "userId":"1",
//             "status":"present"
//         },
//         {
//             "userId":"2",
//             "status":"absent"
//         }
//     ]
// }