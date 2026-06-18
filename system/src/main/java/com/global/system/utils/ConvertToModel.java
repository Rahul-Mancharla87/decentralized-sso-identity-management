package com.global.system.utils;

import com.global.system.ModelConverter.UserEntity;
import com.global.system.model.UserModel;

public class ConvertToModel {

    public UserEntity convertModelEntity(UserModel um){
       
        UserEntity ue=new UserEntity();
        ue.setName(um.getName());
        ue.setAddress(um.getAddress());
        ue.setDepartment(um.getDepartment());
        ue.setEmailId(um.getEmailId());
        ue.setMobile(um.getMobile());
        ue.setPassword(um.getPassword());
        ue.setUserId(um.getUserId());
        ue.setUserType(um.getUserType());



        return ue;
    }
    
}
