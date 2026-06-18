package com.global.system.utils;

import java.util.List;

import com.global.system.model.FileModel;

public class ResponseMessageForFile {
    
    private String message;
    private List<FileModel> fileModel;
   
   
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public List<FileModel> getFileModel() {
        return fileModel;
    }
    public void setFileModel(List<FileModel> fileModel) {
        this.fileModel = fileModel;
    }
   
    
    
    
}
