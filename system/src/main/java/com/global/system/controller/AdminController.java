package com.global.system.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.global.system.service.AdminService;
import com.global.system.utils.AdminResponse;

@RestController
@RequestMapping("/admin")
public class AdminController {

    Logger logger=LoggerFactory.getLogger(AdminController.class);

    @Autowired
    AdminService adminService;


  

    @GetMapping("/getUsers")
    public AdminResponse getUserDeails() {

        return adminService.getUsers();
    }

    @GetMapping("/getFiles")
    public AdminResponse getFiles() {

        return adminService.getFiles();

    }

    

    public AdminResponse getHarmFiles() {
        return null;
    }
    /*
     * @GetMapping("/getHarmFiles")
     * public AdminResponse getHarmFiles(){
     * 
     * return adminService.getHarmFiles();
     * 
     * }
     */

}
