package com.chi.zhang.springboot.SpringDatabaseApp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chi.zhang.springboot.SpringDatabaseApp.model.OrderModel;

@Repository
public interface OrderRepository extends JpaRepository<OrderModel, Long>{
	Page<OrderModel> findByPersonId(Long id, Pageable pageable);
}
