package com.app.service;

import java.util.List;

import com.app.entity.Orders;

public interface IOrderService {
	   List<Orders> placeOrdersFromCart(Integer userId);
       List<Orders>getOrderByUser(Integer userId);
       List<Orders>getAllOrders();
}
