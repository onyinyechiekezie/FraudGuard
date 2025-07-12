package com.fraudguard.fraudguard.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class RegularUserDashboardResponse {

    private String welcomeMessage;
    private List<String> notifications;
    private String todaySummary;
    private int flaggedTransactions;
}
