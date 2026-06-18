package com.cc09.cc09.utils;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

public class SpringSessionUtil {

    public static List<String> getUserId(HttpSession session) {
     
        List<String> user=new ArrayList();
        
        if (session
        .getAttribute("users")==null) {
           user = new ArrayList<>();
        }
        return user;
    }
    
}
