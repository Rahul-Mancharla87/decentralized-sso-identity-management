package com.global.system.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.system.Interfaces.LoginInterface;
import com.global.system.model.UserModel;
import com.global.system.repository.Userrepository;

@Service
public class LoginService {

	@Autowired
	Userrepository userrepository;

	Logger logger = LoggerFactory.getLogger(LoginService.class);

	public UserModel userLogin(String email, String password) {

		LoginInterface loginInterface = (useremail, userpassword) -> {

			UserModel rs = null;
			try {
				rs = userrepository.findByEmailIdAndPassword(useremail, userpassword);

			} catch (Exception e) {

				e.printStackTrace();
			}
			return rs;

		};

		UserModel um = loginInterface.userLogin(email, password);
		return um;

	}

	public UserModel getProfile(String userid) {

		UserModel userModel = userrepository.findById(Integer.parseInt(userid)).get();
		return userModel;
	}

    public UserModel changeStatus(String userid) {
		UserModel userModel = userrepository.findById(Integer.parseInt(userid)).get();
		userModel.setUserStatus("blocked");
		userModel=userrepository.save(userModel);
		return userModel;
    }

}
