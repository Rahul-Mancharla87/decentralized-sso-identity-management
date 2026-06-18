package com.global.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.global.system.model.CheckJWTModel;

@Repository
public interface JwtRepository  extends JpaRepository<CheckJWTModel,Integer>{

    CheckJWTModel findByUserId(int userId);
    
}
