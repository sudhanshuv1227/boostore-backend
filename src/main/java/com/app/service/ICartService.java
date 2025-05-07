package com.app.service;

import java.util.List;

import com.app.entity.CartItem;

public interface ICartService {
    CartItem addToCart(Integer userId, Integer bookId, int quantity);
    List<CartItem> getCartItems(Integer userId);
    void removeFromCart(Long cartItemId);
    void clearCart(Integer userId);
    CartItem updateQuantity(Long cartItemId, int newQuantity);

}
