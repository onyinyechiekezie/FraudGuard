package com.fraudguard.fraudguard.services;

//import com.fraudguard.fraudguard.data.enums.AlertLevel;
//import com.fraudguard.fraudguard.dto.response.AlertValidationResponse;
//import org.springframework.stereotype.Service;
//
//@Service
//public class PatternMatchingAlertValidator implements AlertValidator {
//
//    @Override
//    public AlertValidationResponse validate(String sender, String message) {
//        boolean isFake = false;
//        String reason = "Looks genuine.";
//        AlertLevel level = AlertLevel.SAFE;
//
//        if (!sender.equalsIgnoreCase("UBA") && !sender.equalsIgnoreCase("GTBank")) {
//            isFake = true;
//            reason = "Unrecognized sender";
//            level = AlertLevel.WARNING;
//        }
//
//        if (!message.contains("ref") && !message.contains("REF")) {
//            isFake = true;
//            reason = "Missing reference ID";
//            level = AlertLevel.FRAUD;
//        }
//
//        return new AlertValidationResponse(isFake, level, reason);
//    }
//}
