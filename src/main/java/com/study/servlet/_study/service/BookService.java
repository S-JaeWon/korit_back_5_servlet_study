package com.study.servlet._study.service;

import java.util.List;
import java.util.Map;

import com.study.servlet._study.entity.Book;
import com.study.servlet._study.repository.BookRepository;

public class BookService {
	
	private static BookService instance;
	private BookRepository bookRepository; // 호출 하기 위해 생성
	
	private BookService() {
		bookRepository = BookRepository.getInstance(); // 싱글톤 호출 
	}
	
	public static BookService getInstance() {
		
			if(instance == null) {
				instance = new BookService();
			}
			return instance;
	}
	
	// 2)
	public boolean addBook(Book book) {
		return bookRepository.saveBook(book) > 0;
	}
	
	public Book getBook(int bookId) {
		return bookRepository.findBookByBookId(bookId);
	}
	
	public List<Book> getList(Map<String, String> params) {
		return bookRepository.searchBookList(params);
	}

}
