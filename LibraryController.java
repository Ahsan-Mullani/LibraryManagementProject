package com.example.project.Controller;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.project.Serv.LibraryServiceImplimentation;
import com.example.project.Model.Book;

//This is a Library Controller class with the help of this class client send the Requests
@RestController
@RequestMapping("/libraryManagement.com")


public class LibraryController{

	@Autowired	
	public LibraryServiceImplimentation libraryServImpl;

	public LibraryController(LibraryServiceImplimentation libraryServImpl) {
		this.libraryServImpl = libraryServImpl;
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/Library")
	public ResponseEntity<List<Book>> getAllBooks(@RequestParam(required = false) String title) {
		try {
			@SuppressWarnings("rawtypes")
			List bookList = libraryServImpl.getAllBooks(title);

			if (bookList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(bookList, HttpStatus.OK);  

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//Save Book Data
	@PostMapping("/SaveBook")
	public ResponseEntity<Book> saveBook(@RequestBody Book book){
		System.out.println(book);
		try {
			return new ResponseEntity<>(libraryServImpl.saveBook(book), HttpStatus.CREATED);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//Fetching Specific Record 
	@GetMapping("/readBook/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable("id")  int bookId){
		System.out.println(bookId);
		Optional<Book> book = libraryServImpl.getBookById(bookId);

		if(book.isPresent())
		{
			return new ResponseEntity<>(book.get(),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}


	//Update Respective Record with the help of Id
	@PutMapping("/updateBook/{bookId}")
	public ResponseEntity<Book> updateBookDetailsById(@PathVariable int bookId, @RequestBody Book bookDetails){
		Optional<Book> bookData=libraryServImpl.getBookById(bookId);
		if(bookData.isPresent())
		{
			libraryServImpl.updateBookDetails(bookData, bookDetails);
			return new ResponseEntity<>(libraryServImpl.updateBookDetails(bookData, bookDetails),HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	//Delete Specific Record by Id 
	@DeleteMapping("/deleteBook/{id}")	
	public ResponseEntity<String> deleteBookById(@PathVariable("id")  int bookId){
		Optional<Book> bookData=libraryServImpl.getBookById(bookId);
		try {
			if(bookData.isPresent())
			{
				libraryServImpl.deleteBookById(bookId);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}


	//Delete All Record
	@DeleteMapping("/deleteAllBooks")
	public ResponseEntity<HttpStatus> deleteAllBooks(){
		try
		{
			libraryServImpl.deleteAllBooks();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		catch(Exception exception) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//To find the domain where book is published or not 
	@GetMapping("/library/BookPublished")
	public ResponseEntity<List<Book>> findByPublishedDomain() {
		try {
			List<Book> book =libraryServImpl.findByIsPublishedBook(true);
			if (book.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(book, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
