package com.thbs.attendance.Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Attendance {
    private String batchId;
    private String courseId;
    private String type;
    private String date;
    private List<AttendanceDetail> attendance;
}
