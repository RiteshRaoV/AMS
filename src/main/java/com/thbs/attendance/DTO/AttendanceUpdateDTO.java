package com.thbs.attendance.DTO;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AttendanceUpdateDTO {
    private Long batchId;
    private Long courseId;
    private String date;
    private String type;
    private List<AttendanceDetailDTO> attendance;
}


