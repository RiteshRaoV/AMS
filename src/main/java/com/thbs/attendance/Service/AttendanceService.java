package com.thbs.attendance.Service;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.thbs.attendance.DTO.AttendanceDetailDTO;
import com.thbs.attendance.DTO.AttendanceUpdateDTO;
import com.thbs.attendance.Entity.Attendance;
import com.thbs.attendance.Entity.AttendanceDetail;
import com.thbs.attendance.Repository.AttendanceRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    public Attendance processAttendanceUpdate(AttendanceUpdateDTO attendanceData) {
        Long batchId = attendanceData.getBatchId();
        Long courseId = attendanceData.getCourseId();
        String date = attendanceData.getDate();
        String type = attendanceData.getType();
        List<AttendanceDetailDTO> newAttendance = attendanceData.getAttendance();

        for (AttendanceDetailDTO details : newAttendance) {
            Attendance record = attendanceRepository.findByUserIdAndCourseIdAndBatchId(details.getUserId(), courseId,
                    batchId);
            if (record != null) {
                boolean updated = false;
                List<AttendanceDetail> attendanceList = record.getAttendance();
                for (AttendanceDetail obj : attendanceList) {
                    if (obj.getDate().equals(date) && obj.getType().equals(type)) {
                        obj.setStatus(details.getStatus());
                        updated = true;
                        break;
                    }
                }
                if (!updated) {
                    AttendanceDetail newDetail = new AttendanceDetail(date, type, details.getStatus());
                    attendanceList.add(newDetail);
                }
                attendanceRepository.save(record);
            } else {
                List<AttendanceDetail> attendanceList = new ArrayList<>();
                attendanceList.add(new AttendanceDetail(date, type, details.getStatus()));
                Attendance newAttendanceRecord = new Attendance(batchId, courseId, details.getUserId(), attendanceList);
                attendanceRepository.save(newAttendanceRecord);
            }
        }
        return null;
    }

}
