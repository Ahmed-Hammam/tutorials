package com.tutorial.elasticsearch.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tutorial.elasticsearch.model.Book;
import com.tutorial.elasticsearch.service.BookService;

@RestController
@RequestMapping("/v1/books")
public class BookController {

	//TODO: HATEOAS, controller advice, exception module, logging, read exception from .properties file
	
	@Autowired
	private BookService bookService;
	
	@GetMapping("/health")
	public ResponseEntity<String> healthCheck() {
		return new ResponseEntity<>("Hello!",HttpStatus.OK);
	}
	
	// *********************************** CRUD operations *******************************
	
	@GetMapping("/")
	public ResponseEntity<?> getAllBooks(@RequestParam(required=false) String order){
		if(StringUtils.isNumeric(order))
			return new ResponseEntity<>("invalid order parameter !",HttpStatus.INTERNAL_SERVER_ERROR);
		List<Book> books = bookService.findAll(order);
		if (!CollectionUtils.isEmpty(books))
			return new ResponseEntity<>(books,HttpStatus.OK);
		else
			return new ResponseEntity<>("cannot retrieve data !",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping(value="/book",consumes="application/json")
	public ResponseEntity<?> addBook(@RequestBody Book book){
		Book newBook = bookService.save(book);
		if(newBook!=null)
			return new ResponseEntity<>("Book added successfully. ",HttpStatus.CREATED);
		else
			return new ResponseEntity<>("Error!. cannot add book",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PutMapping(value="/book",consumes="application/json")
	public ResponseEntity<?> editBook(@RequestBody Book book){
		Book newBook = bookService.updateOne(book);
		if(newBook!=null)
			return new ResponseEntity<>("Book edited successfully. ",HttpStatus.OK);
		else
			return new ResponseEntity<>("Error!. cannot edit book",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PatchMapping(value="/book",consumes="application/json")
	public ResponseEntity<?> partialEditBook(@RequestBody Book book){
		Book newBook = bookService.updateOne(book);
		if(newBook!=null)
			return new ResponseEntity<>("Book edited successfully. ",HttpStatus.OK);
		else
			return new ResponseEntity<>("Error!. cannot edit book",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping(value="/book/{id}")
	public ResponseEntity<?> deleteBook(@PathVariable String id) throws Exception {
//		try {
			bookService.delete(id);
			return new ResponseEntity<>("Book deleted successfully. ",HttpStatus.OK);
//		}catch(Exception e) {
//			return new ResponseEntity<>("Error!. cannot delete book",HttpStatus.INTERNAL_SERVER_ERROR);			
//		}
	}
	
	// *********************************** custom operations *******************************
	
	@GetMapping(value="/book/{id}")
	public ResponseEntity<?> getBook(@PathVariable(value = "id") String id){
		Book book = bookService.findOne(id);
		if (book!=null)
			return new ResponseEntity<>(book,HttpStatus.OK);
		else
			return new ResponseEntity<>("cannot retrieve data !",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value="/book/author/{name}")
	public ResponseEntity<?> getBooksForAuthor(@PathVariable(value="name") String authorName){
		List<Book> books = bookService.findByAuthor(authorName, new PageRequest(0, 5));
		if (books!=null)
			return new ResponseEntity<>(books,HttpStatus.OK);
		else
			return new ResponseEntity<>("cannot retrieve data !",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value="/book/search/{term}/{keyword}")
	public ResponseEntity<?> getBooksAccordingTotitleContainsKeyword(@PathVariable String term, @PathVariable String keyword){
		List<Book> books = bookService.findBooksAccordingToContainsKeyword(term,keyword, new PageRequest(0, 5));
		if (books!=null)
			return new ResponseEntity<>(books,HttpStatus.OK);
		else
			return new ResponseEntity<>("cannot retrieve data !",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/book/search/author/{keyword}")
	public ResponseEntity<?> getBooksAccordingTotitleContainsKeyword(@PathVariable String keyword){
		List<Book> book = bookService.findByIncompleteAuthorName(keyword, new PageRequest(0, 5));
		if (book!=null)
			return new ResponseEntity<>(book,HttpStatus.OK);
		else
			return new ResponseEntity<>("cannot retrieve data !",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
