package com.global.system.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.global.system.Dto.UserRegistrationDto;
import com.global.system.Exceptions.MyException;
import com.global.system.blockchain.BlockChainFile;
import com.global.system.blockchain.BlockChainService1;
import com.global.system.model.CheckJWTModel;
import com.global.system.model.FileModel;
import com.global.system.model.User;
import com.global.system.model.UserModel;
import com.global.system.repository.JwtRepository;
import com.global.system.service.LoginService;
import com.global.system.service.UserImplementaion;
import com.global.system.service.UserService;
import com.global.system.utils.BlockChainResponseMessage;
import com.global.system.utils.ResponseMessage;

@RestController
@RequestMapping("/user")
@CrossOrigin("http://localhost:8081")
public class UserController {
   
    @Autowired
    UserService userService;
    @Autowired
    UserImplementaion userImplementaion;
    @Autowired
    ResponseMessage responseMessage;
    @Autowired
    LoginService loginService;
     @Autowired
    JwtRepository jwtRepository;

    @Autowired
    BlockChainService1 blockChainService1;

    Logger logger= LoggerFactory.getLogger(UserController.class);
    public static final String fail="failed";

    @GetMapping("/hi")
    public ResponseEntity<List<String>> home(){
        List<String> list=new ArrayList<>();
        list.add("shyam");
        return new ResponseEntity<List<String>>(list, HttpStatus.CREATED);
    }

    @PostMapping("/seRegister")
    public String registerUser1(@RequestBody UserRegistrationDto userModel){

      User user=  userImplementaion.userRegistration(userModel);

        return "success";
    }

    @PostMapping("/Register")
    public ResponseMessage registerUser(@RequestBody UserModel userModel) throws MyException, SQLException {

        int i = userService.regUser(userModel);
        if (i != -1) {
            if (i != 10) {
                responseMessage.setMessage("successs");
                logger.info("User success");
            } else {
                responseMessage.setMessage("email");
                logger.info("email id present use another email id");
            }
        } else {
            responseMessage.setMessage(fail);
            logger.error("error occured");

        }
        return responseMessage;
    }

    @PostMapping("/login")
    public ResponseMessage checkLogin(@RequestBody UserModel userModel) {
        UserModel um = loginService.userLogin(userModel.getEmailId(), userModel.getPassword());
        ResponseMessage rm = new ResponseMessage();

        if (um != null) {
         
           if(um.getUserStatus().equalsIgnoreCase("blocked")){
                rm.setMessage("User is Inactive !please contact Admin to activate the user");
                rm.setUserModel(um);
            }else{
                String message=userService.checkSmartContract(um);
              
                rm.setMessage("success");
                rm.setSmartMessage(message);
                rm.setUserModel(um);
            }
           


        } 
        else {
            rm.setMessage(fail);
            rm.setUserModel(um);
        }
        return rm;
    }

    @GetMapping("/profile/{userid}")
    public ResponseMessage checkLogin(@PathVariable String userid) {
        UserModel um = loginService.getProfile(userid);
        if (um != null) {
            ResponseMessage rm = new ResponseMessage();
            rm.setMessage("success");
            rm.setUserModel(um);
            return rm;
        } else {
            ResponseMessage rm = new ResponseMessage();
            rm.setMessage(fail);
            return rm;
        }
    }

    @GetMapping("/changeUserStatus/{userid}")
    public ResponseMessage changeUserStatus(@PathVariable String userid) {
        UserModel um = loginService.changeStatus(userid);
        if (um != null) {
            ResponseMessage rm = new ResponseMessage();
            rm.setMessage("User status changed");
            rm.setUserModel(um);
            return rm;
        } else {
            ResponseMessage rm = new ResponseMessage();
            rm.setMessage("failed to change the user status");
            return rm;
        }
    }


    @GetMapping("/block/{userId}")
    public BlockChainResponseMessage applyBlcok(@PathVariable String userId) throws IOException{
System.out.println("in block Service"+userId);
        int fileId= userService.getBlockService();
        FileModel fileModel=userService.getFilesToImplementBlockChain(fileId);
        
        BlockChainFile bcf=new BlockChainFile();
         bcf.setUserId(Integer.parseInt(userId));bcf.setFileId(fileModel.getFileId());
         bcf.setOldFileHash(fileModel.getHashKey());
         
         BlockChainFile blockChainFile=blockChainService1.process(bcf);

        BlockChainResponseMessage bcrm=new BlockChainResponseMessage();
        bcrm.setNewHash(blockChainFile.getNewFileHash());
        bcrm.setOldHash(blockChainFile.getOldFileHash());
        bcrm.setUserId(Integer.parseInt(userId));
        bcrm.setFileId(fileModel.getFileId());
        return bcrm;
    }

    @GetMapping("/checkJwt/{userId}")
public long checkJwt(@PathVariable int userId){

long i=userService.checkJWTtoken(userId);
System.out.println("time is"+i);
if(i==-1){

}else if(i>5){
CheckJWTModel cJwtModel= jwtRepository.findByUserId(userId);
jwtRepository.delete(cJwtModel);
}else{

}


return i;

}
    


}
