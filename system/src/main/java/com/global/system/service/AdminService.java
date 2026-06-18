package com.global.system.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.system.model.FileModel;
import com.global.system.repository.FileRepository;
import com.global.system.repository.Userrepository;
import com.global.system.utils.AdminResponse;

@Service
public class AdminService {

  @Autowired
  Userrepository userrepository;
  @Autowired
  FileRepository fileRepository;

  Logger logger=LoggerFactory.getLogger(AdminService.class);

  public AdminResponse getUsers() {

    AdminResponse ar = new AdminResponse();
    ar.setMessage("success");
    ar.setUserModels(userrepository.findAll());
    return ar;
  }

  public AdminResponse getFiles() {

    AdminResponse ar = new AdminResponse();
    ar.setMessage("success");
    ar.setFileModels(fileRepository.findAll());
    List<FileModel> fileModel = ar.getFileModels();

    for (FileModel fileModel2 : fileModel) {
      checkVurnability(fileModel2.getFileName());
    }

    return ar;
  }

  private void checkVurnability(String fileName) {


     File file = new File("http://localhost:8081/upload/files/"+fileName);
    
     BufferedReader  br=null;
      StringBuffer  sb=null;
    try {
       FileReader fr = new FileReader(file);
       br = new BufferedReader(fr);
       sb = new StringBuffer();
      String line;
      while ((line = br.readLine()) != null) {
        logger.info(line);
        sb.append(line);
        sb.append("\n");
      }
      fr.close();
      br.close();
      
      logger.info("contents of file");
    } catch (NullPointerException e) {
      e.printStackTrace();
    }catch (IOException e) {
      e.printStackTrace();
    }
    

  }

}
