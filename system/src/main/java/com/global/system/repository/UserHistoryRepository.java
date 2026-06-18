package com.global.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.global.system.model.UserLoginHistoryModel;

@Repository
public interface UserHistoryRepository extends JpaRepository<UserLoginHistoryModel,Integer> {

    List<UserLoginHistoryModel> findByUserId(int userId);

   
    
}
