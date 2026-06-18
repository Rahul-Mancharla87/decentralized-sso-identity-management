package com.global.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.global.system.model.UserModel;

public interface Userrepository extends JpaRepository<UserModel, Integer> {

    @Query(value = "select * from user_model", nativeQuery = true)
    List<UserModel> findByEmailId();

    UserModel findByEmailIdAndPassword(String useremail, String userpassword);

    @Query(value="SELECT * FROM file_model ORDER BY file_id DESC LIMIT 1",nativeQuery = true)
    int getLastFileId();

    @Query(value = "select * from user_model where email_id=?1", nativeQuery = true)
    UserModel findByEmailId1(String toMail);

}
                                                                                                                             