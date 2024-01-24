package com.study.servlet._study.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Book {
	private int bookId;
	private String bookName;
	private Author author;
	private Publisher publisher;
	
}
