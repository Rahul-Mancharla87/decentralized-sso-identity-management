package com.global.system.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CheckJWTModel {

    @Id
    @GeneratedValue
    int jwtId;
    String jwtToken;
    String endTime;
    int userId;


    public int getJwtId() {
        return jwtId;
    }
    public void setJwtId(int jwtId) {
        this.jwtId = jwtId;
    }
    public String getJwtToken() {
        return jwtToken;
    }
    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
    public String getEndTime() {
        return endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    
    

}
