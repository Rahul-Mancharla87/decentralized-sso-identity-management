package com.cc09.cc09.controller;





import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cc09.cc09.Exceptions.MyException;
import com.cc09.cc09.config.Configuration;
import com.cc09.cc09.model.AdminResponse;

@Controller
public class AdminContorller {

    Logger logger=LoggerFactory.getLogger(AdminContorller.class);
    
    @GetMapping("/adminHome")
    public String adminHome(Model model) throws MyException {
       System.out.println("hi home page");
        
  model.addAttribute("home", "home");
        return "Admin";

    }

    @GetMapping("/users")
    public String usersRecords(Model model) throws MyException {

        AdminResponse rm = null;
        try {
            rm = Configuration
                    .getRestTemplate()
                    .getForObject(Configuration.getIP() + "admin/getUsers", AdminResponse.class);
        } catch (Exception e) {
              logger.error("Error occured server is not reachable");
            throw new MyException("your server is not reachable");
          
        }

        model.addAttribute("userspage", "users");
        model.addAttribute("users", rm.getUserModels());

        return "Admin";

    }

    @GetMapping("/files")
    public String userFiles(Model model) throws MyException {

        AdminResponse rm = null;
        try {
            rm = Configuration
                    .getRestTemplate()
                    .getForObject(Configuration.getIP() + "admin/getFiles", AdminResponse.class);
        } catch (Exception e) {
            throw new MyException("your server is not reachable");
        }

        model.addAttribute("filespage", "filespage");
        model.addAttribute("files", rm.getFileModels());

        return "Admin";

    }

    @GetMapping("/harmFiles")
    public String harmFiles(Model model) throws MyException {

        AdminResponse rm = null;
        try {
            rm = Configuration
                    .getRestTemplate()
                    .getForObject(Configuration.getIP() + "admin/getHarmFiles", AdminResponse.class);
        } catch (Exception e) {
            throw new MyException("your server is not reachable");
        }

        model.addAttribute("harmFiles", "harmFiles");
        model.addAttribute("files", rm.getFileModels());

        return "Admin";

    }

}
