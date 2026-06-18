package com.global.system.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.global.system.TimeUtilsMethods.LocalDateToString;
import com.global.system.model.CheckJWTModel;
import com.global.system.model.UserModel;
import com.global.system.repository.JwtRepository;
import com.global.system.repository.Userrepository;
import com.global.system.service.EmailService;
import com.global.system.utils.HashCodeGenearate;

@RestController
public class EmailController {

   @Autowired
   EmailService emailService;

   @Autowired
   JwtRepository jwtRepository;

   @Autowired
   Userrepository userrepository;

   @GetMapping("/sendEmail")
   public String sendEmail(@RequestParam("toMail") String toMail,
         @RequestParam("subject") String subject,
         @RequestParam("message") String msg) {

      return emailService.sendMail(toMail, "Token ", HashCodeGenearate.getHashValue(toMail));

   }

   @GetMapping("/sendToken/{toMail}")
   public String sendToken(@PathVariable String toMail) {

      LocalDateTime fiveMinutesLater = LocalDateTime.now().plusMinutes(5);
      String hashcode=HashCodeGenearate.getHashValue(toMail);

      CheckJWTModel cjm=new CheckJWTModel();
     
      
      UserModel um= userrepository.findByEmailId1(toMail);
    
     try{
     cjm=jwtRepository.findByUserId(um.getUserId());
     cjm.setJwtToken(hashcode);cjm.setEndTime(LocalDateToString.convertToSring(fiveMinutesLater));
     cjm.setUserId(um.getUserId());
     
     jwtRepository.save(cjm);
     }catch(Exception e){
      CheckJWTModel cjm1=new CheckJWTModel();
      cjm1.setJwtToken(hashcode);cjm1.setEndTime(LocalDateToString.convertToSring(fiveMinutesLater));
     cjm1.setUserId(um.getUserId());
     
     jwtRepository.save(cjm1);
     }
     
     
      return emailService.sendMail(toMail, "Token ", HashCodeGenearate.getHashValue(toMail));

   }

}
