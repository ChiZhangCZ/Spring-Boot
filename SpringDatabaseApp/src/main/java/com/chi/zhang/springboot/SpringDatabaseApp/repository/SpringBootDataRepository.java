package com.chi.zhang.springboot.SpringDatabaseApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chi.zhang.springboot.SpringDatabaseApp.model.SpringBootDataModel;

@Repository
public interface SpringBootDataRepository extends JpaRepository<SpringBootDataModel,Long>{

}
