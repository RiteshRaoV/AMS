package com.thbs.attendance.Repository;

import com.thbs.attendance.Entity.Attendance;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepo extends MongoRepository<Attendance, ObjectId> {
    // Define custom query methods if needed
}

