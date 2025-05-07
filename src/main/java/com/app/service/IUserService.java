package com.app.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.app.entity.User;

public interface IUserService {
	public ResponseEntity<?> registerUser(User user);
	public ResponseEntity<?> loginUser(User user);
	
    ResponseEntity<List<User>> getAllUsers();

    ResponseEntity<?> toggleUserStatus(Integer userId);

    ResponseEntity<?> getUserById(Integer userId);
}

