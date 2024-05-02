package com.thbs.attendance.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private long employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private String businessUnit;
    
}