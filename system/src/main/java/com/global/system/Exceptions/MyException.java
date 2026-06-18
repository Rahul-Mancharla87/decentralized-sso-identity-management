package com.global.system.Exceptions;

public class MyException extends Exception {

    String message;

    public MyException(String message) {
        //super(message);
        this.message = message;
    }

}
