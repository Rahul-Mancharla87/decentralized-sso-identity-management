package com.cc09.cc09.controller;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cc09.cc09.Exceptions.MyException;
import com.cc09.cc09.Repository.UserStatusRepository;
import com.cc09.cc09.config.Configuration;
import com.cc09.cc09.model.UserModel;
import com.cc09.cc09.model.UserReport;
import com.cc09.cc09.utils.Infomation;
import com.cc09.cc09.utils.ResponseMessage;

@Controller
@SessionAttributes({ "um", "ur" })
public class LoginController {

    @Autowired
    UserStatusRepository userStatusRepository;

    @PostMapping("/loginsubmit")
    public String userLogin(
            @RequestParam("emailid") String emailid,
            @RequestParam("password") String password, Model model, HttpSession session, HttpServletRequest request)
            throws MyException {

        if (emailid.equalsIgnoreCase("admin@gmail.com") && password.equalsIgnoreCase("admin")) {
    

            model.addAttribute("home", "home");
            return "Admin";
        } else {

            UserModel um = new UserModel();
            um.setEmailId(emailid);
            um.setPassword(password);
            ResponseMessage rm = null;
            try {
                rm = Configuration
                        .getRestTemplate()
                        .postForObject(Configuration.getIP() + "user/login", um, ResponseMessage.class);
            } catch (Exception e) {
                throw new MyException("your server is not reachable");
            }
            if (rm.getMessage().equalsIgnoreCase("success")) {
               
                if(rm.getSmartMessage().equalsIgnoreCase("smart")){
                    um = rm.getUserModel();
                    
                  String msg=  Configuration
                        .getRestTemplate()
                        .getForObject(Configuration.getIP() +"sendToken/"+emailid, String.class);
                        System.out.println("mail notification"+msg);
                   /*  UserReport ur = new UserReport();
                    ur.setUserId(um.getUserId());
                    ur.setLoginDateAndTime(LocalDate.now().toString());
                    ur.setLoginStatus("1");
                    ur = userStatusRepository.save(ur); */
                    model.addAttribute("suid", um.getUserId());
                    model.addAttribute("um", um);
                    model.addAttribute("smart", "Smart Contract Created");
                    return "index";

                }else if(rm.getSmartMessage().equalsIgnoreCase("modified")){
                    String info = Infomation.getErrorMessage("Hash has modified you cannot login");
                    model.addAttribute("info", info);
                    return "login";

                }else{

                    um = rm.getUserModel();
                    /* UserReport ur = new UserReport();
                    ur.setUserId(um.getUserId());
                    ur.setLoginDateAndTime(LocalDate.now().toString());
                    ur.setLoginStatus("1");
                    ur = userStatusRepository.save(ur); */
                    String msg=  Configuration
                    .getRestTemplate()
                    .getForObject(Configuration.getIP() +"sendToken/"+emailid, String.class);
                    System.out.println("mail notification"+msg);
                    model.addAttribute("suid", um.getUserId());
                    model.addAttribute("um", um);
                    model.addAttribute("smart", "Smart contract replaced with new Hash");
                    return "index";


                }
               
           
            } else {
                String info = Infomation.getErrorMessage("Emailid or password wrong");
                model.addAttribute("info", info);
                return "login";
            }

        }

    }

    @GetMapping("/profile/{userid}")
    public String checkProfile(@PathVariable String userid, Model model) {

        ResponseMessage rm = null;
        try {

         long tokentime= Configuration
                    .getRestTemplate()
                    .getForObject(Configuration.getIP() + "user/checkJwt/"+userid, Long.class);
           
        if(tokentime==-1){

            String info = Infomation.getErrorMessage("JWT token is not Generated Please login");
                model.addAttribute("info", info);
                return "login";

        }else if(tokentime>5) {

            String info = Infomation.getErrorMessage("Token has expired ");
            model.addAttribute("info", info);
            return "login";

        }else{    
          
                    rm = Configuration
                    .getRestTemplate()
                    .getForObject(Configuration.getIP() + "user/profile/" + userid, ResponseMessage.class);

            if (rm.getMessage().equalsIgnoreCase("success")) {
                model.addAttribute("um", rm.getUserModel());
                model.addAttribute("profile", "profile");
                return "index";
            } else {
                model.addAttribute("error", "Error Occured ");
                model.addAttribute("profile", "profile");
                return "index";
            }
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";

    }

    @GetMapping("/logout")
    public String Logout() {
        return "login";
    }

}
