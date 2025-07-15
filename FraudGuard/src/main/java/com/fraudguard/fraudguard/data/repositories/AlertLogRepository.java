package com.fraudguard.fraudguard.data.repositories;

import com.fraudguard.fraudguard.data.models.AlertLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AlertLogRepository extends MongoRepository<AlertLog, String> {

    List<AlertLog> findByUserIdAndTimestampBetween(String userId, LocalDateTime start, LocalDateTime end);

}
