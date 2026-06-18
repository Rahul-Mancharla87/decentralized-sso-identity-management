package com.global.system.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.global.system.blockchain.BlockChainFile;
import com.global.system.model.FileModel;
import com.global.system.repository.FileBlockRepository;
import com.global.system.repository.FileRepository;
import com.global.system.utils.CryptoUtilsTest;
import com.global.system.utils.HashCodeGenearate;

@Service
public class FileUploadService {

  private final Path root = Paths.get("uploads");
  private final Path enroot = Paths.get("enc");

  Logger logger = LoggerFactory.getLogger(FileUploadService.class);

  @Autowired
  FileRepository fileRepository;

  @Autowired
  FileBlockRepository fileBlockRepository;

  public void init() {
    try {
      Files.createDirectories(root);
      Files.createDirectories(enroot);
    } catch (IOException e) {
      throw new RuntimeException("Could not initialize folder for upload!");
    }
  }

  public void save(MultipartFile file, int userId) {
    try {
      Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
      encryptFile(file, userId);

    } catch (Exception e) {
      throw new RuntimeException("A file of that name already exists.");

    }
  }

  public void encryptFile(MultipartFile file, int userId) throws IOException {
    InputStream inputStream = file.getInputStream();
    // need to get emailid and passwrd
    String genearatehash = "emailid" + "password";
    FileUploadService fd = new FileUploadService();
    Resource resource = fd.load(file.getOriginalFilename());
    File f = resource.getFile();
    File ecncryptFile1 = new File(this.enroot.toAbsolutePath().toFile() + "/enc" + file.getOriginalFilename());
    CryptoUtilsTest.encrptAlgoritham("shyamshyamshyamm", f, ecncryptFile1);
    String hashValue = HashCodeGenearate.getHashValue(genearatehash);
    FileModel fm = new FileModel();
    fm.setUserId(userId);
    fm.setFileName(file.getOriginalFilename());
    fm.setHashKey(hashValue);
    fileRepository.save(fm);
  }

  public Resource load(String filename) {
    try {
      Path file = root.resolve(filename);
      Resource resource = new UrlResource(file.toUri());

      if (resource.exists() || resource.isReadable()) {
        return resource;
      } else {
        throw new RuntimeException("Could not read the file!");
      }
    } catch (MalformedURLException e) {
      throw new RuntimeException("Error: " + e.getMessage());
    }
  }

  public void deleteAll() {
    FileSystemUtils.deleteRecursively(root.toFile());
  }

  public Stream<Path> loadAll() {
    try {
      return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
    } catch (IOException e) {
      throw new RuntimeException("Could not load the files!");
    }
  }

/* public FileModel checFile(FileModel fm) {

 // List<FileModel> files=new ArrayList<>();
  BlockChainFile bcf=  fileBlockRepository.findByFileId(fm.getFileId());

  if(fm.getHashKey().equalsIgnoreCase(bcf.getOldFileHash())){
     
      fm.setDomain("Hash");
      return fm;
  }else{
   
      fm.setDomain("Hash Modified ");
     return fm;
  }

    
} */
}
