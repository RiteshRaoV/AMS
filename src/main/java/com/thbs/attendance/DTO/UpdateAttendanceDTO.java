package com.thbs.attendance.DTO;

import lombok.Data;

import java.util.List;

@Data
public class UpdateAttendanceDTO {
    private String batchId;
    private String courseId;
    private String type;
    private String date; 
    private List<AttendanceDetailDTO> attendance;
}

