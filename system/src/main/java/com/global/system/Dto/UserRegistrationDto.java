package com.global.system.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserRegistrationDto {

   
    private String userName;
    private String userEmail;
    private String userPassword;
    private String userAddress;
    private String userMobile;

}
