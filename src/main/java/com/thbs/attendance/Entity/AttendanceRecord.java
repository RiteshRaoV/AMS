package com.thbs.attendance.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AttendanceRecord {
    private String date;
    private String type;
    private String attendanceStatus;
}
