package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.entity.User;
import com.app.repo.UserRepository;
import com.app.wrapper.UserResponseWrapper;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository repo;

    @Override
    public ResponseEntity<?> registerUser(User user) {
        UserResponseWrapper wrapper = new UserResponseWrapper();

        if (!user.getPassword().equals(user.getCfmPassword())) {
            wrapper.setMessage("Password and Confirm Password do not match.");
            wrapper.setData(null);
            return new ResponseEntity<>(wrapper, HttpStatus.FORBIDDEN);
        } else {
            User savedUser = repo.save(user);
            wrapper.setMessage("Registration Successful!");
            wrapper.setData(savedUser);
            return new ResponseEntity<>(wrapper, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> loginUser(User user) {
        UserResponseWrapper wrapper = new UserResponseWrapper();

        Optional<User> foundUser = repo.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (foundUser.isPresent()) {
            if (!foundUser.get().isStatus()) {
                wrapper.setMessage("Account is deactivated. Contact Admin.");
                wrapper.setData(null);
                return new ResponseEntity<>(wrapper, HttpStatus.UNAUTHORIZED);
            }

            wrapper.setMessage("Login Successful!");
            wrapper.setData(foundUser.get());
            return new ResponseEntity<>(wrapper, HttpStatus.OK);
        } else {
            wrapper.setMessage("Invalid Credentials!");
            wrapper.setData(null);
            return new ResponseEntity<>(wrapper, HttpStatus.NOT_FOUND);
        }
    }

	@Override
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = repo.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> toggleUserStatus(Integer userId) {
		  Optional<User> optionalUser = repo.findById(userId);
		    if (optionalUser.isEmpty()) {
		        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
		    }

		    User user = optionalUser.get();
		    user.setStatus(!user.isStatus()); // Toggle the status
		    User updatedUser = repo.save(user); // Save the updated user

		    // Log the status change for debugging
		    System.out.println("User status updated: " + updatedUser.getUsername() + " -> " + updatedUser.isStatus());

		    return ResponseEntity.ok(updatedUser); // Return the updated user
        }
	

	@Override
	public ResponseEntity<?> getUserById(Integer userId) {
		 Optional<User> user = repo.findById(userId);
	        return user.isPresent()
	                ? new ResponseEntity<>(user.get(), HttpStatus.OK)
	                : new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
	        }
}
