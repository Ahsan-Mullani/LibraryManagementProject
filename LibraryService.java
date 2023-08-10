package com.example.project.Services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.project.Model.Book;

//This is Library Service Interface which we need to implement and provide definition to abstract method

@Service
public interface LibraryService{
	Book saveBook(Book book);
	List<Book>getAllBooks(String str);
	Optional<Book> getBookById(int bookId);
	Book updateBookDetails(Optional<Book> book, Book newVal);
	void deleteBookById(int bookId);
	void deleteAllBooks();
}