package com.study.servlet._study.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.servlet._study.entity.Book;
import com.study.servlet._study.service.BookService;

@WebServlet("/books")
public class BookListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookService bookService;
       
    public BookListServlet() {
        super();
        bookService = BookService.getInstance();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookName = request.getParameter("bookName");
		String authorName = request.getParameter("authorName");
		String publisherName = request.getParameter("publisherName");
		
		Map<String, String> params = new HashMap<>();
		
		if(bookName != null) {
			params.put("bookName", bookName);
		}
		
		if(authorName != null) {
			params.put("authorName", authorName);
		}
		
		if(publisherName != null) {
			params.put("publisherName", publisherName);
		}
		
		List<Book> books = bookService.getList(params);
		response.setContentType("text/plain");
		response.setStatus(201);
		response.getWriter().println(books);
		
	}

}
