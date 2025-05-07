package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.entity.User;
import com.app.service.IUserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private IUserService service;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        return service.registerUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        return service.loginUser(user);
    }
    @PutMapping("/toggle-status/{userId}")
    public ResponseEntity<?> toggleUserStatus(@PathVariable Integer userId) {
        return service.toggleUserStatus(userId);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() {
        return service.getAllUsers();
    }
}
