package com.thbs.attendance.DTO;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import com.thbs.attendance.Entity.AttendanceDetail;

@Data
public class UpdateAttendanceDTO {
    private String batchId;
    private String courseId;
    private String type;
    private String date;
    private List<AttendanceDetailDTO> attendance;
    public List<AttendanceDetail> attendanceDtoToAttendanceDetails(){
        List<AttendanceDetail> res=new ArrayList<>();
        for(AttendanceDetailDTO attendanceDto:this.attendance){
            AttendanceDetail attendanceDetail=new AttendanceDetail(attendanceDto.getUserId(),attendanceDto.getStatus());
            res.add(attendanceDetail);
        }
        return res;
    }
}






