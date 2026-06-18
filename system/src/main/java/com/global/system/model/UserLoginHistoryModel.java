package com.global.system.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserLoginHistoryModel {

    @Id
    @GeneratedValue
    int historyId;
    int userId;
    String oldHash;
    String newHash;
    String loginTime;
    String logoutTime;
    
    public int getHistoryId() {
        return historyId;
    }
    public void setHistoryId(int historyId) {
        this.historyId = historyId;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getOldHash() {
        return oldHash;
    }
    public void setOldHash(String oldHash) {
        this.oldHash = oldHash;
    }
    public String getNewHash() {
        return newHash;
    }
    public void setNewHash(String newHash) {
        this.newHash = newHash;
    }
    public String getLoginTime() {
        return loginTime;
    }
    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }
    public String getLogoutTime() {
        return logoutTime;
    }
    public void setLogoutTime(String logoutTime) {
        this.logoutTime = logoutTime;
    }

    




    
}
