package com.global.system.Interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.global.system.model.UserModel;

public interface UserInterface {

    public ResultSet getEmails()throws SQLException;
    public int registerUser(UserModel userModel)throws SQLException;

}
