package com.fraudguard.fraudguard.data.repositories;

import com.fraudguard.fraudguard.data.models.ActivityLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ActivityLogRepository extends MongoRepository<ActivityLog, String> {

    long countByUserIdAndActionAndTimestampBetween(
            String userId,
            String action,
            LocalDateTime start,
            LocalDateTime end
    );
}
