package com.global.system.service;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.global.system.Dto.UserRegistrationDto;
import com.global.system.model.Role;
import com.global.system.model.User;
import com.global.system.repository.UserSecurityRepository;

@Service
public class UserImplementaion implements UserSecurityService {

    UserSecurityRepository userSecurityRepository;

    UserImplementaion(UserSecurityRepository userSecurityRepository){
        this.userSecurityRepository=userSecurityRepository;
    }

    @Override
    public User userRegistration(UserRegistrationDto urd) {

     User user= new User(urd.getUserName(), 
      urd.getUserEmail(), 
      urd.getUserPassword(), 
      urd.getUserAddress(), 
      urd.getUserMobile(),
      Arrays.asList(new Role("role_user")));

      return userSecurityRepository.save(user);
     
    }
    
}
