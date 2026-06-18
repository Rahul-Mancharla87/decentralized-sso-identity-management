package com.global.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.global.system.model.FileModel;

@Repository
public interface FileRepository extends JpaRepository<FileModel,Integer> {    

    @Query(value = "SELECT * FROM file_model WHERE user_id = ?1", nativeQuery = true)
    List<FileModel> findByuserId(int i);

    
    
}
