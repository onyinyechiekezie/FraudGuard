package com.fraudguard.fraudguard.services;

import com.fraudguard.fraudguard.data.models.RegularUser;
import com.fraudguard.fraudguard.dto.response.DailySummaryResponse;
import com.fraudguard.fraudguard.dto.response.NotificationResponse;
import com.fraudguard.fraudguard.dto.response.RegularUserDashboardResponse;

import java.util.List;

public interface RegularUserService {

    RegularUser getRegularUserByToken(String token);

    RegularUserDashboardResponse getDashboardData(String token);

    NotificationResponse viewNotification(String token, String notificationId);

    List<NotificationResponse> viewDailyNotifications(String token);

    DailySummaryResponse viewDailySummary(String token);
}
