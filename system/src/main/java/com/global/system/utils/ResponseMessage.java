package com.global.system.utils;

import org.springframework.stereotype.Component;

import com.global.system.model.FileModel;
import com.global.system.model.UserModel;

@Component
public class ResponseMessage {

    String message;
    UserModel userModel;
    FileModel fileModel;
    String smartMessage;

    public ResponseMessage(){
        
    }

    public ResponseMessage(String msg) {
        this.message=msg;
    }
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

    public FileModel getFileModel() {
        return fileModel;
    }

    public void setFileModel(FileModel fileModel) {
        this.fileModel = fileModel;
    }

    public String getSmartMessage() {
        return smartMessage;
    }

    public void setSmartMessage(String smartMessage) {
        this.smartMessage = smartMessage;
    }

    
    
    
}
