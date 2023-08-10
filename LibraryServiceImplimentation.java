package com.example.project.Serv;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.project.Model.Book;
import com.example.project.Services.LibraryService;
import com.example.project.Repo.LibraryRepository;


//from the interface LibraryService implement the abstract methods and use annotations
@Service
public class LibraryServiceImplimentation implements LibraryService{

	LibraryRepository bookRepository;

	public LibraryServiceImplimentation(LibraryRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public Optional<Book> getBookById(int bookId) { 
		Optional<Book> book=bookRepository.findById(bookId);
		return book;
	}
	
	//Update The Book Details
	@Override
	public Book updateBookDetails( Optional<Book> bookDetails,Book newVal) {

		Book book =bookDetails.get();
		book.setBookName(newVal.getBookName());
		book.setPrice(newVal.getPrice());
		book.setNoCopies(newVal.getNoCopies());
		book.setAuthorName(newVal.getAuthorName());
		book.setPublished(newVal.isPublished());
		return bookRepository.save(book);
	}

	//Get Book By BookName
	public Book getBookByName(int bookId) { 
		Optional<Book> book = bookRepository.findById(bookId);  
		System.out.println(bookId);
		System.out.println(book.isPresent());
		if(book.isPresent()) {
			Book obj = book.get();
			System.out.println(obj);
			return book.get();
		}
		else {return null;}
	}

	//Get Book Details By Book Name
	public List<Book>getBookDetailsByName(String name) {
		List<Book> bookList = new ArrayList<>();
		bookRepository.findByBookNameContainingIgnoreCase(name).forEach(bookList::add);
		return bookList;
	}

	//Delete All Records
	public void deleteAllBooks() {
		bookRepository.deleteAll();
	}

	//To Save Book Data
	@Override
	public Book saveBook(Book book) {
		return bookRepository.save(book);  
	}

	//Delete Respective book Record with the help of Id
	@Override
	public void deleteBookById(int bookId) {
		bookRepository.deleteById(bookId);
	}

	//Fetch All Data
	@Override
	public List<Book> getAllBooks(String title) {
		List<Book> book = new ArrayList<Book>();
		if (title == null) 
			bookRepository.findAll().forEach(book::add);
		else
			bookRepository.findByBookNameContainingIgnoreCase(title).forEach(book::add);
		return  book;
	}

	//Fetch the record which is published
	public List<Book> findByIsPublishedBook(boolean status) {
		return bookRepository.findByIsPublished(status);
	}
}












