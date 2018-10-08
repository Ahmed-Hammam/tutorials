package com.tutorial.elasticsearch.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.tutorial.elasticsearch.model.Book;

public interface BookService {

	Book save(Book book);

	void delete(String bookId) throws Exception;

	Book findOne(String id);
	
	Book updateOne(Book book);

	List<Book> findAll(String order);

	List<Book> findByAuthor(String author, PageRequest pageRequest);
	
	List<Book> findBooksAccordingToContainsKeyword(String term, String keyword, PageRequest pageRequest); //title search
	
	List<Book> findByIncompleteAuthorName(String keyword, PageRequest pageRequest);

	List<Book> findByTitle(String title);
}
