package com.thbs.attendance.Repository;



import com.thbs.attendance.Entity.Attendance;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends MongoRepository<Attendance, String> {
    Attendance findByUserIdAndCourseIdAndBatchId(Long userId, Long batchId, Long courseId);

    List<Attendance> findByBatchIdAndCourseId(Long batchId,Long courseId);

    List<Attendance> findByBatchId(Long batchId);

}


