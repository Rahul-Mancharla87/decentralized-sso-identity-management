package com.global.system.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.global.system.Interfaces.UserInterface;
import com.global.system.model.UserModel;

@Repository
public class UserDao implements UserInterface {

    @Autowired private DataSource datasource;

    @Override public ResultSet getEmails()throws SQLException {
        Connection con = datasource.getConnection();
        String sql = "select * from register";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        return rs;
    }

    @Override public int registerUser(UserModel userModel)throws SQLException {
        Connection con = datasource.getConnection();
        String sql = "insert into register values(0,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, userModel.getName());
        ps.setString(2, userModel.getEmailId());
        ps.setString(3, userModel.getPassword());
        ps.setString(4, userModel.getDepartment());
        int statusReg = ps.executeUpdate();
        return statusReg;
    }

}
