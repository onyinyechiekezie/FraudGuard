package com.fraudguard.fraudguard.services;

public interface FraudDetectionService {
    void scanIncomingMessage(String userId, String sender, String message, String userPhone);
//    void scanIncomingMessage(String sender, String message);
}
