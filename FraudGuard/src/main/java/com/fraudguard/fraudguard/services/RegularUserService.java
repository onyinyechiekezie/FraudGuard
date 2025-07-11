package com.fraudguard.fraudguard.services;

import com.fraudguard.fraudguard.data.models.RegularUser;

public interface RegularUserService {

    RegularUser getRegularUserByToken(String token);
}
