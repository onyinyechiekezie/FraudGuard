package com.fraudguard.fraudguard.services;

import com.fraudguard.fraudguard.data.models.RegularUser;
import com.fraudguard.fraudguard.dto.response.RegularUserDashboardResponse;

public interface RegularUserService {

    RegularUser getRegularUserByToken(String token);

    RegularUserDashboardResponse getDashboardData(String token);
}
