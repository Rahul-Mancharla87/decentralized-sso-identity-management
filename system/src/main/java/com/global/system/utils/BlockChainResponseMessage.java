package com.global.system.utils;

public class BlockChainResponseMessage {

    private String oldHash;
    private String newHash;
    private int userId;
    private int fileId;
    
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
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getFileId() {
        return fileId;
    }
    public void setFileId(int fileId) {
        this.fileId = fileId;
    }


    
    
}
