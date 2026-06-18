package com.global.system.model;

import org.springframework.web.multipart.MultipartFile;

public class FileRequestModel {

    int userid;
    MultipartFile file;
    
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}

    
    
}
