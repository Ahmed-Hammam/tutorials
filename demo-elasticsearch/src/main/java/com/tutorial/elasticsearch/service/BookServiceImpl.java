package com.tutorial.elasticsearch.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.queryparser.xml.builders.BooleanQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RegexpQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.tutorial.elasticsearch.model.Book;
import com.tutorial.elasticsearch.repository.BooksRepository;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
	private BooksRepository bookRepository;

	@Override
	public Book save(Book book) {
		return bookRepository.save(book);
	}

	@Override
	public void delete(String bookId) throws Exception {
			Book book = bookRepository.findOne(bookId);
			if(book!=null)
				bookRepository.delete(bookId);
			else
				throw new Exception("Error!. cannot delete book");
	}

	@Override
	public Book findOne(String id) {
		return bookRepository.findOne(id);
	}

	@Override
	public List<Book> findAll(String order) {
		Iterable<Book> books = bookRepository.findAll(new Sort(Sort.Direction.ASC, order==null?"id":order));
		List<Book> booksList = new ArrayList<Book>();
		books.forEach(booksList::add);
		return booksList; 
	}

	@Override
	public List<Book> findByAuthor(String author, PageRequest pageRequest) {
		Page<Book> books = bookRepository.findByAuthor(author, pageRequest);
		return books.getContent();
	}

	@Override
	public List<Book> findByTitle(String title) {
		return bookRepository.findByTitle(title);
	}

	@Override
	public Book updateOne(Book book) {
		return bookRepository.index(book);
	}

	@Override
	public List<Book> findBooksAccordingToContainsKeyword(String term, String keyword, PageRequest pageRequest) {
		RegexpQueryBuilder qb = QueryBuilders.regexpQuery(term, ".*"+keyword+"*.");
		SearchQuery query = new NativeSearchQueryBuilder().withFilter(qb).build();
		Page<Book> books = bookRepository.search(query);
		return books.getContent();
	}

	@Override
	public List<Book> findByIncompleteAuthorName(String keyword, PageRequest pageRequest) {
		MatchQueryBuilder qb = QueryBuilders.matchQuery("author", keyword);
		SearchQuery query = new NativeSearchQueryBuilder().withFilter(qb).build();
		Page<Book> books = bookRepository.search(query);
		return books.getContent();
	}

}
