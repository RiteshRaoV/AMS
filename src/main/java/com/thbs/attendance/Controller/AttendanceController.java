package com.thbs.attendance.Controller;



import com.thbs.attendance.DTO.UpdateAttendanceDTO;
import com.thbs.attendance.Service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/updateAttendance")
    public ResponseEntity<?> updateAttendance(@RequestBody UpdateAttendanceDTO updateAttendanceDTO) {
        attendanceService.updateAttendance(updateAttendanceDTO);
        return ResponseEntity.ok().build();
    }
}



// {
//     "batchId":"1",
//     "courseId":"1",
//     "userId":"1"
//     "attendance":[
//         {
//             "date":"30/1/2101",
//             "type":"1stHalf",
//             "status":"present"
//         },
//         {
//             "date":"31/1/2101",
//             "type":"1stHalf",
//             "status":"present"
//         }
//     ]
// }