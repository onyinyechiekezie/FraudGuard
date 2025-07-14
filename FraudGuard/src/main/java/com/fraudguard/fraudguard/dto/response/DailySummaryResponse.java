package com.fraudguard.fraudguard.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DailySummaryResponse {

    private int totalAlerts;
    private int fraudAlerts;
    private int safeAlerts;
    private String date;
}
