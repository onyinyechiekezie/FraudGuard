package com.fraudguard.fraudguard.data.repositories;

import com.fraudguard.fraudguard.data.models.TransactionLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionLogRepository extends MongoRepository<TransactionLog, String> {

    int countByUserIdAndIsFlaggedAndTimestampBetween(
            String userId,
            boolean isFlagged,
            LocalDateTime start,
            LocalDateTime end
    );
    @Query("{ 'userId': ?0, 'isFlagged': true }")
    List<TransactionLog> findAllFlaggedByUser(String userId);

    @Query("{ 'userId': ?0, 'isFlagged': true, 'timestamp': { $gte: ?1, $lte: ?2 } }")
    List<TransactionLog> findFlaggedBetween(String userId, LocalDateTime start, LocalDateTime end);
}
