package com.thbs.attendance.Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Attendance {
    @Id
    private String id;
    private Long batchId;
    private Long courseId;
    private Long userId;
    private List<AttendanceDetail> attendance;

    public Attendance(Long batchid,Long courseId,Long userId,List<AttendanceDetail> attendanceDetails){
        this.batchId=batchid;
        this.courseId=courseId;
        this.userId=userId;
        this.attendance=attendanceDetails;
    }
}
