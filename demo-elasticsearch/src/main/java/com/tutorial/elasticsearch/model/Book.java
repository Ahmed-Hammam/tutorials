package com.tutorial.elasticsearch.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.Data;

@Document(indexName="tutorials",type="books")
@Data
public class Book {

	@Id
    private String id;
    private String title;
    private String author;
    private String releaseDate;
    private String description;
    
	@Override
	public String toString() {
		  return "Book{" +
	                "id='" + id + '\'' +
	                ", title='" + title + '\'' +
	                ", author='" + author + '\'' +
	                ", releaseDate='" + releaseDate + '\'' +
	                ", description='" + description + '\'' +
	                '}';
	}
    
    
}
