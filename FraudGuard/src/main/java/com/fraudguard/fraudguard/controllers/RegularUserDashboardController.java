package com.fraudguard.fraudguard.controllers;

import com.fraudguard.fraudguard.data.models.RegularUser;
import com.fraudguard.fraudguard.dto.response.RegularUserDashboardResponse;
import com.fraudguard.fraudguard.services.RegularUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/regular")
@RequiredArgsConstructor
public class RegularUserDashboardController {

    private final RegularUserService regularUserService;

    @GetMapping
    public ResponseEntity<RegularUserDashboardResponse> getDashboard(
            @RequestHeader("Session-Token") String token
    ) {
        RegularUserDashboardResponse response = regularUserService.getDashboardData(token);
        return ResponseEntity.ok(response);
    }
}
