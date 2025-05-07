package com.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.entity.CartItem;

public interface CartRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUserId(Integer userId);
    void deleteByUserId(Integer userId);
}
