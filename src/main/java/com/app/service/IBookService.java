package com.app.service;

import java.util.List;
import java.util.Optional;

import com.app.entity.Book;

public interface IBookService {
	  Book addBook(Book book, Integer userId);
	    List<Book> getAllBooks();
	    List<Book> getBooksByUser(Integer userId);
	    Book getBookById(Integer id);
	    Book updateBook(Integer id, Book book);
	    void deleteBook(Integer id);
	    List<Book> searchBooks(String query);
}


