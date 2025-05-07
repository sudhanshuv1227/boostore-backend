package com.app.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.app.entity.CartItem;
import com.app.service.ICartService;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "http://localhost:3000")
public class CartController {

    @Autowired
    private ICartService cartService;

    @PostMapping("/add")
    public CartItem addToCart(@RequestParam Integer userId,
    		                  @RequestParam Integer bookId,
    		                  @RequestParam int quantity) {
        return cartService.addToCart(userId, bookId, quantity);
    }

    @GetMapping("/{userId}")
    public List<CartItem> getCart(@PathVariable Integer userId) {
        return cartService.getCartItems(userId);
    }

    @DeleteMapping("/remove/{id}")
    public void removeFromCart(@PathVariable Long id) {
        cartService.removeFromCart(id);
    }

    @DeleteMapping("/clear/{userId}")
    public void clearCart(@PathVariable Integer userId) {
        cartService.clearCart(userId);
    }
    
    @PutMapping("/update/{cartItemId}")
    public CartItem updateQuantity(@PathVariable Long cartItemId, @RequestBody CartItem updatedItem) {
        return cartService.updateQuantity(cartItemId, updatedItem.getQuantity());
    }

}
