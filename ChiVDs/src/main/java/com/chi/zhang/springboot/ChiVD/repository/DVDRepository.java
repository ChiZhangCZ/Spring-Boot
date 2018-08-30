package com.chi.zhang.springboot.ChiVD.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chi.zhang.springboot.ChiVD.model.DVDModel;

public interface DVDRepository extends JpaRepository<DVDModel,Long>{

}
