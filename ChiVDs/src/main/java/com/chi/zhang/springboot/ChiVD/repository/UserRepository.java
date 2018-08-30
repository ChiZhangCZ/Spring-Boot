package com.chi.zhang.springboot.ChiVD.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chi.zhang.springboot.ChiVD.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel,Long>{

}
