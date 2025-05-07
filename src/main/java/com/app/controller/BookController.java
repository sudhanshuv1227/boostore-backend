package com.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.Book;
import com.app.entity.User;
import com.app.repo.BookRepository;
import com.app.repo.UserRepository;
import com.app.service.BookServiceImpl;
@CrossOrigin(origins = "http://localhost:3000") 
@RestController
@RequestMapping("/book")
public class BookController {
	@Autowired
	private BookServiceImpl bookService;
	
	@Autowired
	private UserRepository userRepo; // ✅ Make sure this is injected
	
	@Autowired
	private BookRepository bookRepo;


	@PostMapping("/add/{userId}")
	public ResponseEntity<?> addBook(@RequestBody Book book, @PathVariable Integer userId) {
	    Optional<User> userOpt = userRepo.findById(userId);
	    if (userOpt.isPresent()) {
	        book.setOwner(userOpt.get()); // set user reference if Book has `@ManyToOne`
	        Book saved = bookRepo.save(book);
	        return ResponseEntity.ok(saved);
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
	    }
	}

	@GetMapping("all")
	public List<Book> getAllBooks() {
	    return bookService.getAllBooks();
	}

	@GetMapping("/user/{userId}")
	public List<Book> getBooksByUser(@PathVariable Integer userId) {
	    return bookService.getBooksByUser(userId);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable Integer id) {
	    return ResponseEntity.ok(bookService.getBookById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateBook(@PathVariable Integer id, @RequestBody Book updatedBook) {
	    Book updated = bookService.updateBook(id, updatedBook);
	    return ResponseEntity.ok(updated);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteBook(@PathVariable Integer id) {
	    bookService.deleteBook(id);
	    return ResponseEntity.ok("Book deleted.");
	}

	@GetMapping("/search")
	public List<Book> searchBooks(@RequestParam String query) {
	    return bookService.searchBooks(query);
	}

}
