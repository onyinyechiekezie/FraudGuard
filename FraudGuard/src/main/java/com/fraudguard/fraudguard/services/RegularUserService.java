package com.fraudguard.fraudguard.services;

import com.fraudguard.fraudguard.data.models.RegularUser;
import com.fraudguard.fraudguard.dto.response.NotificationResponse;
import com.fraudguard.fraudguard.dto.response.RegularUserDashboardResponse;

import java.util.List;

public interface RegularUserService {

    RegularUser getRegularUserByToken(String token);

    RegularUserDashboardResponse getDashboardData(String token);

}
