package com.study.servlet._study.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println(request.getMethod());
		System.out.println(request.getRequestURL());
		System.out.println(request.getRequestURI());
		System.out.println(response.getStatus());
		
		response.setContentType("text/plain");
		//response.setCharacterEncoding("UTF-8"); 따로 필터를 걸어줌
		System.out.println(response.getContentType());
		
		response.getWriter().println("헬로");
		
		System.out.println("요청이 들어옴");
	}

}
