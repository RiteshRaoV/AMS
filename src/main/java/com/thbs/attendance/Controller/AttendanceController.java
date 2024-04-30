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