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

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    private final MongoClient mongoClient;
    private final MongoCollection<Document> attendanceCollection;

    public AttendanceService() {
        this.mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("AMS");
        this.attendanceCollection = database.getCollection("attendance");
    }

    public Attendance processAttendanceUpdate(AttendanceUpdateDTO attendanceData) {
        Long batchId = attendanceData.getBatchId();
        Long courseId = attendanceData.getCourseId();
        String date = attendanceData.getDate();
        String type = attendanceData.getType();
        List<AttendanceDetailDTO> newAttendance = attendanceData.getAttendance();
        for (AttendanceDetailDTO details : newAttendance) {
            Attendance record = attendanceRepository.findByUserIdAndBatchIdAndCourseId(details.getUserId(), courseId, batchId);
            if(record!=null){
                AttendanceDetail detail=new AttendanceDetail(date,type,details.getStatus());
                record.getAttendance().add(detail);
                attendanceRepository.save(record);
            }else{
                AttendanceDetail detail = new AttendanceDetail(date,type,details.getStatus());
                List<AttendanceDetail> attendanceList = new ArrayList<>();
                attendanceList.add(detail);
                Attendance attendance = new Attendance(batchId, courseId,details.getUserId(), attendanceList);
                attendanceRepository.save(attendance);
             }
        }
        return null;
    }
}
