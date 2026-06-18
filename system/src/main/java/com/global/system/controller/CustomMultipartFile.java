package com.global.system.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

public class CustomMultipartFile implements MultipartFile {

    private final byte[] fileContent;

    private String fileName;

    private String contentType;

    private File file;

    private String destPath = System.getProperty("c:\\");

    private FileOutputStream fileOutputStream;

    public CustomMultipartFile(byte[] fileData, String name) {
        this.fileContent = fileData;
        this.fileName = name;

       file = new File("C:/"+fileName);

    }

    public File getFile() {
        return file;

    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {
        fileOutputStream = new FileOutputStream(dest);
        fileOutputStream.write(fileContent);
    }

    public void clearOutStreams() throws IOException {
        if (null != fileOutputStream) {
            fileOutputStream.flush();
            fileOutputStream.close();
            file.deleteOnExit();
        }
    }

    @Override

    public byte[] getBytes() throws IOException {
        return fileContent;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(fileContent);
    }

    @Override
    public String getName() {
        
        return null;
    }

    @Override
    @Nullable
    public String getOriginalFilename() {

        return fileName;
    }

    @Override
    @Nullable
    public String getContentType() {
        
        return null;
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public long getSize() {
        // TODO Auto-generated method stub
        return 0;
    }

}
