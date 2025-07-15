package com.fraudguard.fraudguard.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DailySummaryResponse {

    private int totalAlerts;
    private int fraudAlerts;
    private int safeAlerts;
    private String date;
}
