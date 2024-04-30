package com.thbs.attendance.Service;

import com.thbs.attendance.DTO.AttendanceDetailDTO;
import com.thbs.attendance.DTO.UpdateAttendanceDTO;
import com.thbs.attendance.Entity.Attendance;
import com.thbs.attendance.Entity.AttendanceRecord;
import com.thbs.attendance.Repository.AttendanceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepo attendanceRepository;

    public void updateAttendance(UpdateAttendanceDTO updateAttendanceDTO) {
        Attendance attendance = new Attendance();
        attendance.setBatchId(Integer.parseInt(updateAttendanceDTO.getBatchId()));
        attendance.setCourseId(Integer.parseInt(updateAttendanceDTO.getCourseId()));
        attendance.setDates(new ArrayList<>());

        for (AttendanceDetailDTO detail : updateAttendanceDTO.getAttendance()) {
            AttendanceRecord record = new AttendanceRecord();
            record.setDate(detail.getDate()); // Set date from DTO
            record.setType(updateAttendanceDTO.getType());
            record.setAttendanceStatus(detail.getStatus());
            attendance.getDates().add(record);
        }

        attendanceRepository.save(attendance);
    }
}

