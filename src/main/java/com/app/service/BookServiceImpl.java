package com.app.service;

import com.app.entity.Book;
import com.app.entity.User;
import com.app.repo.BookRepository;
import com.app.repo.UserRepository;
import com.app.service.IBookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements IBookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Book addBook(Book book, Integer userId) {
        User owner = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        book.setOwner(owner); // updated
        return bookRepository.save(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> getBooksByUser(Integer userId) {
        return bookRepository.findAll().stream()
                .filter(book -> book.getOwner().getId().equals(userId)) // updated
                .toList();
    }

    @Override
    public Book getBookById(Integer id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    @Override
    public Book updateBook(Integer id, Book updatedBook) {
        Book book = getBookById(id);
        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setPrice(updatedBook.getPrice());
        book.setCategory(updatedBook.getCategory());
        book.setDescription(updatedBook.getDescription());
        book.setImageUrl(updatedBook.getImageUrl());
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Integer id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> searchBooks(String query) {
        return bookRepository.findByTitleContainingIgnoreCase(query);
    }
}
