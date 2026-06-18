package com.cc09.cc09.utils;

public class Infomation {

    public static String getMessage(String message){
        return "<div class=\"alert alert-success wrap-input100\">\n" +
        "                        <p style=\"font-family: Ubuntu-Bold; font-size: 18px;"+
       " margin: 0.25em 0; text-align: center\">\n" +
        message+"\n"+
        "                        </p>\n" +
        "<a href=\"login\">Login</a> "+
        "                    </div>";
    }

    
    public static String getErrorMessage(String message){
        return "<div class=\"alert alert-success wrap-input100\">\n" +
        "                        <p style=\"font-family: Ubuntu-Bold; font-size: 18px;"+
       " margin: 0.25em 0; text-align: center\">\n" +
        message+"\n"+
       
        "                    </div>";
    }
    
}
