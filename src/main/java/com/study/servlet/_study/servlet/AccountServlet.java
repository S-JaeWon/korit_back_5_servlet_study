package com.study.servlet._study.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.servlet._study.entity.Account;
import com.study.servlet._study.service.AccountService;

@WebServlet("/account")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private AccountService accountService;
    
    public AccountServlet() {
        super();
        accountService = AccountService.getInstance(); //서블렛 객체 생성시 같이 생성
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username"); //-1 서비스 거쳐서 레파지토리로 
		
		Account account = accountService.getAccount(username);
		response.setStatus(200);
		response.getWriter().println(account);
		//post에서 send 하고 get에서 send 해야함. 값이 저장되지 않기 때문
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
//		String username = request.getParameter("username"); 
//		String password =
//		request.getParameter("password"); 
//		String name = request.getParameter("name");
//		String email = request.getParameter("email");
		 
		//-> 하나의 객체로 묶기 가능, 패키지 엔티티
		
		Account account = Account.builder()
				.username(request.getParameter("username"))
				.password(request.getParameter("password"))
				.name(request.getParameter("name"))
				.email(request.getParameter("email"))
				.build();
		
		//TODO service repository 나누기 순서
		int body = accountService.addAccount(account); // 1)account 객체 넘겨주고 addAccount 생성 //서비스에서 받은 리턴 값 1, body에 대입 
		
		response.setStatus(201);
		response.getWriter().println(body);
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	

}
