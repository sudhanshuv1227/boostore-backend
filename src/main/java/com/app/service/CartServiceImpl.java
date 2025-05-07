package com.app.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.entity.Book;
import com.app.entity.CartItem;
import com.app.entity.User;
import com.app.repo.BookRepository;
import com.app.repo.CartRepository;
import com.app.repo.UserRepository;

@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BookRepository bookRepo;

    @Override
    public CartItem addToCart(Integer userId, Integer bookId, int quantity) {
        User user = userRepo.findById(userId).orElseThrow();
        Book book = bookRepo.findById(bookId).orElseThrow();
        List<CartItem> userCart = cartRepo.findByUserId(userId);
        for (CartItem item : userCart) {
            if (item.getBook().getId().equals(bookId)) {
                item.setQuantity(item.getQuantity() + quantity);
                return cartRepo.save(item);
            }
        }

        // If not found, create new cart item
        CartItem cartItem = new CartItem();
        cartItem.setUser(user);
        cartItem.setBook(book);
        cartItem.setQuantity(quantity);
        return cartRepo.save(cartItem);
    }

    @Override
    public List<CartItem> getCartItems(Integer userId) {
        return cartRepo.findByUserId(userId);
    }

    @Override
    public void removeFromCart(Long cartItemId) {
        cartRepo.deleteById(cartItemId);
    }

    @Override
    public void clearCart(Integer userId) {
        cartRepo.deleteByUserId(userId);
    }
    @Override
    public CartItem updateQuantity(Long cartItemId, int newQuantity) {
        CartItem cartItem = cartRepo.findById(cartItemId)
            .orElseThrow(() -> new RuntimeException("Cart item not found"));

        cartItem.setQuantity(newQuantity);
        return cartRepo.save(cartItem);
    }

}
