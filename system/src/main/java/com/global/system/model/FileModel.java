package com.global.system.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class FileModel {
	
    @Id
    @GeneratedValue
	int FileId;
	int UserId;
	String fileName;
	String hashKey;
	String domain;
	String fileUrl;
	int status;
	
	public FileModel() {
	}
	public FileModel(int fileId,String hashkey,String fileName, String fileUrl) {
		this.fileName = fileName;
		this.fileUrl = fileUrl;
		this.FileId=fileId;
		this.hashKey=hashkey;
	}

	public FileModel(String fileName, String fileUrl) {
		this.fileName = fileName;
		this.fileUrl = fileUrl;
		
	}
	public FileModel(String fileName, String fileUrl,int status,int fileId) {
		this.fileName = fileName;
		this.fileUrl = fileUrl;
		this.status=status;
		this.FileId=fileId;
	}
	public int getFileId() {
		return FileId;
	}
	public void setFileId(int fileId) {
		FileId = fileId;
	}
	public int getUserId() {
		return UserId;
	}
	public void setUserId(int userId) {
		UserId = userId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getHashKey() {
		return hashKey;
	}
	public void setHashKey(String hashKey) {
		this.hashKey = hashKey;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	

}

