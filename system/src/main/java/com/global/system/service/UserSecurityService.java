package com.global.system.service;

import com.global.system.Dto.UserRegistrationDto;
import com.global.system.model.User;

public interface UserSecurityService {

    User userRegistration(UserRegistrationDto urd);
    
}
