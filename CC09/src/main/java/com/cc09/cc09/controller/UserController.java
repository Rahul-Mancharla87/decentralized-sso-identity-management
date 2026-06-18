package com.cc09.cc09.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.cc09.cc09.config.Configuration;
import com.cc09.cc09.model.UserModel;
import com.cc09.cc09.utils.Infomation;
import com.cc09.cc09.utils.ResponseMessage;

@Controller
public class UserController {

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }
    @GetMapping("/")
    public String test(HttpSession session) {
    
        return "login";
      
    }

    @GetMapping("registerForm")
    public String registerFrom() {
        return "register";
    }

    @PostMapping("/register")
    public String userRegister(
     @RequestParam("name") String userName,
     @RequestParam("mobile")String mobile,
     @RequestParam("emailid")String emailid,
     @RequestParam("password")String password, Model model){

        UserModel um= new UserModel();
        um.setName(userName);um.setMobile(mobile);um.setEmailId(emailid);um.setPassword(password);
        ResponseMessage rm=Configuration
        .getRestTemplate()
        .postForObject(Configuration.getIP()+"user/Register", um, ResponseMessage.class);

        if(rm.getMessage().equalsIgnoreCase("successs")){
            String info = Infomation.getMessage("Register Success");
          model.addAttribute("info", info);
     
        }
        if(rm.getMessage().equalsIgnoreCase("email")){
            String info = Infomation.getErrorMessage("Registration Failed");
          model.addAttribute("info", info);

        }
        return "register";

    }

}
