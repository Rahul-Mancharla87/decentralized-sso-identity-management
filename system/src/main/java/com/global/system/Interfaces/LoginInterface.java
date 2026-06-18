package com.global.system.Interfaces;

import com.global.system.model.UserModel;

@FunctionalInterface
public interface LoginInterface {
    public UserModel userLogin(String email, String password);
}
