package com.app.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.CartItem;
import com.app.entity.Orders;
import com.app.entity.User;
import com.app.repo.BookRepository;
import com.app.repo.CartRepository;
import com.app.repo.OrderRepository;
import com.app.repo.UserRepository;
@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
	private OrderRepository orderRepo;
    
	@Autowired
	private BookRepository bookRepo;
	
	@Autowired
	private UserRepository userRepo; 
	
	@Autowired
	private CartRepository cartRepo;
	
	@Override
	public List<Orders> placeOrdersFromCart(Integer userId) {
	    User user = userRepo.findById(userId).orElseThrow();
	    List<CartItem> cartItems = cartRepo.findByUserId(userId);

	    if (cartItems.isEmpty()) throw new RuntimeException("Cart is empty");

	    List<Orders> placedOrders = new ArrayList<>();

	    for (CartItem item : cartItems) {
	        Orders order = new Orders();
	        order.setUser(user);
	        order.setBook(item.getBook());
	        order.setQuantity(item.getQuantity());
	        order.setTotalPrice(item.getBook().getPrice() * item.getQuantity());
	        order.setOrderDate(LocalDateTime.now());
	        placedOrders.add(orderRepo.save(order));
	    }

	    cartRepo.deleteAll(cartItems); // clear cart after placing order

	    return placedOrders;
	}


	@Override
	public List<Orders> getOrderByUser(Integer userId) {
		return orderRepo.findByUserId(userId);
	}

	@Override
	public List<Orders> getAllOrders() {
		return orderRepo.findAll();
	}


}
