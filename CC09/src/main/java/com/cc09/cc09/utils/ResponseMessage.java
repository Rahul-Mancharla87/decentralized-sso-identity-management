package com.cc09.cc09.utils;

import org.springframework.stereotype.Component;

import com.cc09.cc09.model.UserModel;



@Component
public class ResponseMessage {

    String message;
    UserModel userModel;
    String smartMessage;

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public UserModel getUserModel() {
        return userModel;
    }
    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }
    public String getSmartMessage() {
        return smartMessage;
    }
    public void setSmartMessage(String smartMessage) {
        this.smartMessage = smartMessage;
    }

    
    
}
