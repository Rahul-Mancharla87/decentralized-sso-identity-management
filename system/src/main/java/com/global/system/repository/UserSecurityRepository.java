package com.global.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.global.system.model.User;

@Repository
public interface UserSecurityRepository extends JpaRepository<User,Long> {
    
}
