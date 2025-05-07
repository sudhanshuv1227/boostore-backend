package com.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Orders;

public interface OrderRepository extends JpaRepository<Orders, Long>{
     List<Orders>findByUserId(Integer userId);
}
