package com.global.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.global.system.model.EndpointsModel;


@Repository
public interface EndPointRepository extends JpaRepository<EndpointsModel,Integer> {
    
}
