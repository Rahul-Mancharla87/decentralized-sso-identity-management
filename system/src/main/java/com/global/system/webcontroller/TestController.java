package com.global.system.webcontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.global.system.config.Configuration;
import com.global.system.model.UserModel;
import com.global.system.utils.ResponseMessage;

@RestController
public class TestController {

    @GetMapping("/test")
    public ResponseMessage getUserModel() {

        UserModel userModel = new UserModel();
        userModel.setName("shyam");
        userModel.setEmailId("shyam7@gmail.com");
        userModel.setPassword("123456");
        ResponseMessage result = Configuration
            .getRestTemplate()
            .postForObject(
                Configuration.getIP() + "user/Register",
                userModel,
                ResponseMessage.class
            );
        return result;
    }

    @PostMapping("/login")
    public ResponseMessage testLogin(@RequestBody UserModel um) {
        System
            .out
            .println(um.getEmailId() + um.getPassword());
        ResponseMessage result = Configuration
            .getRestTemplate()
            .postForObject(
                Configuration.getIP() + "/user/login",
                um,
                ResponseMessage.class
            );
        return result;
    }

}
