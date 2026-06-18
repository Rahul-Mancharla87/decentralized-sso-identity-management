package com.global.system.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.global.system.model.FileModel;
import com.global.system.repository.FileRepository;
import com.global.system.service.FileUploadService;
import com.global.system.utils.ResponseMessage;
import com.global.system.utils.ResponseMessageForFile;

import org.springframework.core.io.Resource;

@RestController
@RequestMapping("/upload")
public class FileUploadController  {

  @Autowired
  FileUploadService fileUploadService;
  @Autowired
  FileRepository fileRepository;
  Logger logger = LoggerFactory.getLogger(FileUploadController.class);

  @PostMapping("/fileUpload")
  public ResponseEntity<ResponseMessage> fileUpload(@RequestParam("file") byte[] file,
      @RequestParam("userid") String userid, @RequestParam("fileName") String fileName) {

    try {

      CustomMultipartFile customMultipartFile = new CustomMultipartFile(file, fileName);
      try {
        customMultipartFile.transferTo(customMultipartFile.getFile());

      } catch (IllegalStateException e) {
        e.printStackTrace();
        logger.error("error occured");

      } catch (IOException e) {
        e.printStackTrace();
        logger.error("error occured");

      }
      fileUploadService.save(customMultipartFile, Integer.parseInt(userid));
      String message = "success";
      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
    } catch (Exception e) {
      e.printStackTrace();
      String message = "failed";
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
    }

  }

  @GetMapping("/files")
  public ResponseEntity<List<FileModel>> getListFiles() {

    List<FileModel> fileInfos = fileUploadService.loadAll().map(path -> {

      String filename = path.getFileName().toString();
      

      String url = MvcUriComponentsBuilder
          .fromMethodName(FileUploadController.class, "getFile", path.getFileName().toString()).build().toString();

      return new FileModel(filename, url);
    }).collect(Collectors.toList());
    return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
  }

  @GetMapping("/files/{filename:.+}")
  @ResponseBody
  public ResponseEntity<Resource> getFile(@PathVariable String filename) {
    Resource file = fileUploadService.load(filename);
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
  }

  @GetMapping("/userFiles/{userId}")
  public ResponseMessageForFile getListFiles(@PathVariable String userId) {

    List<FileModel> filess = fileRepository.findByuserId(Integer.parseInt(userId));
    List<FileModel> fileInfos;
    List<FileModel> finalFileInfos=new ArrayList<>();
    fileInfos = filess.stream().map(path -> {

      String filename = path.getFileName();
      int fileId=path.getFileId();
      String hashKey=path.getHashKey();

      String url = MvcUriComponentsBuilder
          .fromMethodName(FileUploadController.class, "getFile", path.getFileName()).build().toString();

      return new FileModel(fileId,hashKey,filename, url);
    }).collect(Collectors.toList());

    ResponseMessageForFile rmf = new ResponseMessageForFile();
    rmf.setMessage("success");
   
    rmf.setFileModel(fileInfos);
    return rmf;
  }

  @GetMapping("/userHarmFiles/{userId}")
  public ResponseMessageForFile getHarmListFiles(@PathVariable String userId) {

    List<FileModel> filess = fileRepository.findByuserId(Integer.parseInt(userId));
    List<FileModel> fileInfos;
    fileInfos = filess.stream().map(path -> {

      String filename = path.getFileName().toString();

      String url = MvcUriComponentsBuilder
          .fromMethodName(FileUploadController.class, "getFile", path.getFileName().toString()).build().toString();
      int status = path.getStatus();
      int fileId = path.getFileId();
      return new FileModel(filename, url, status, fileId);
    }).collect(Collectors.toList());

    ResponseMessageForFile rmf = new ResponseMessageForFile();
    rmf.setMessage("success");
    fileInfos = fileInfos.stream().filter(s -> s.getStatus() == 1).collect(Collectors.toList());
    rmf.setFileModel(fileInfos);
    return rmf;
  }

  @RequestMapping("/deleteFile/{fileId}")
  public String deleteFile(@PathVariable String fileId) {

    FileModel fm = fileRepository.getReferenceById(Integer.parseInt(fileId));
    fileRepository.delete(fm);
    return "success";

  }

}
