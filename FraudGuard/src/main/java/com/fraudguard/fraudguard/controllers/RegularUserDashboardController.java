package com.fraudguard.fraudguard.controllers;

import com.fraudguard.fraudguard.data.models.RegularUser;
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

    @GetMapping("/dashboard")
    public ResponseEntity<RegularDashboardResponse> getDashboard(
            @RequestHeader("X-Auth-Token") String token) {

        RegularUser user = regularUserService.getRegularUserByToken(token);

        RegularDashboardResponse response = new RegularDashboardResponse(
                "Welcome back, " + user.getFirstname(),
                List.of("🔔 Transaction alert received", "📣 New fraud tip available"),
                "You’ve had 3 verified transactions today"
        );

        return ResponseEntity.ok(response);
    }
}
