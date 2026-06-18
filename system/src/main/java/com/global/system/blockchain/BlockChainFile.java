package com.global.system.blockchain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.global.system.model.FileModel;
import com.global.system.model.UserModel;

@Entity
public class BlockChainFile {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long blockId;
    private int fileId;
    private int userId;
    private String oldFileHash;
    private String newFileHash;

    public long getBlockId() {
        return blockId;
    }
    public void setBlockId(long blockId) {
        blockId = blockId;
    }
    public int getFileId() {
        return fileId;
    }
    public void setFileId(int fileId) {
        this.fileId = fileId;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getOldFileHash() {
        return oldFileHash;
    }
    public void setOldFileHash(String oldFileHash) {
        this.oldFileHash = oldFileHash;
    }
    public String getNewFileHash() {
        return newFileHash;
    }
    public void setNewFileHash(String newFileHash) {
        this.newFileHash = newFileHash;
    }


    
}
