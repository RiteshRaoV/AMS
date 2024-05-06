package com.thbs.attendance.Service;

import com.thbs.attendance.DTO.AttendanceDetailDTO;
import com.thbs.attendance.DTO.AttendanceUpdateDTO;
import com.thbs.attendance.DTO.EmployeeAttendanceDTO;
import com.thbs.attendance.DTO.EmployeeDTO;
import com.thbs.attendance.Entity.Attendance;
import com.thbs.attendance.Entity.AttendanceDetail;
import com.thbs.attendance.Repository.AttendanceRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private BatchService batchService;

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

    public List<EmployeeAttendanceDTO> getAttendanceDetails(Long batchID, Long courseId, String date, String type)
            throws IllegalArgumentException {
        List<EmployeeDTO> employees = batchService.getEmployeesByBatchId(batchID);
        List<Attendance> attendances = attendanceRepository.findByBatchIdAndCourseId(batchID, courseId);
        if (!attendances.isEmpty()) {
            Map<Long, String> attendanceMap = new HashMap<>();

            for (Attendance attendance : attendances) {
                for (AttendanceDetail detail : attendance.getAttendance()) {
                    if (detail.getDate().equals(date) && detail.getType().equals(type)) {
                        attendanceMap.put(attendance.getUserId(), detail.getStatus());
                    }
                }
            }

            List<EmployeeAttendanceDTO> response = new ArrayList<>();
            for (EmployeeDTO employee : employees) {
                EmployeeAttendanceDTO employeeAttendanceDTO = new EmployeeAttendanceDTO();
                employeeAttendanceDTO.setEmployeeId(employee.getEmployeeId());
                employeeAttendanceDTO.setFirstName(employee.getFirstName());
                employeeAttendanceDTO.setLastName(employee.getLastName());
                employeeAttendanceDTO.setEmail(employee.getEmail());
                employeeAttendanceDTO.setBusinessUnit(employee.getBusinessUnit());
                employeeAttendanceDTO.setRole(employee.getRole());

                String status = attendanceMap.get(employee.getEmployeeId());
                if (status != null) {
                    employeeAttendanceDTO.setStatus(status);
                }

                response.add(employeeAttendanceDTO);
            }

            return response;
        }
        return null;
    }

}
