package com.cc09.cc09.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cc09.cc09.model.UserReport;

@Repository
public interface UserStatusRepository extends JpaRepository<UserReport,Integer> {
    
}
