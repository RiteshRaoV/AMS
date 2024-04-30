package com.thbs.attendance.Service;



import com.thbs.attendance.DTO.UpdateAttendanceDTO;
import com.thbs.attendance.Entity.Attendance;
import com.thbs.attendance.Repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    public void updateAttendance(UpdateAttendanceDTO updateAttendanceDTO) {
        Attendance attendance = new Attendance();
        attendance.setBatchId(updateAttendanceDTO.getBatchId());
        attendance.setCourseId(updateAttendanceDTO.getCourseId());
        attendance.setType(updateAttendanceDTO.getType());
        attendance.setDate(updateAttendanceDTO.getDate());
        attendance.setAttendance(updateAttendanceDTO.attendanceDtoToAttendanceDetails());
        attendanceRepository.save(attendance);
    }
}
