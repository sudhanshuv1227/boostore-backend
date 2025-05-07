package com.app.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.Orders;
import com.app.service.OrderServiceImpl;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;

    @PostMapping("/place-from-cart/{userId}")
    public List<Orders> placeFromCart(@PathVariable Integer userId) {
        return orderService.placeOrdersFromCart(userId);
    }


    @GetMapping("/user/{userId}")
    public List<Orders> getOrderByUser(@PathVariable Integer userId) {
        return orderService.getOrderByUser(userId);
    }

    @GetMapping("/all")
    public List<Orders> getAllOrders() {
        return orderService.getAllOrders(); // Admin only
    }
}
