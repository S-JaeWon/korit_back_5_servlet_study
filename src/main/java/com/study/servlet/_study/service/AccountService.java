package com.study.servlet._study.service;

import com.study.servlet._study.entity.Account;
import com.study.servlet._study.repository.AccountRepository;

public class AccountService {
	
	private static AccountService instance;
	
	private AccountRepository accountRepository; //서비스에서 레파지토리 호출하기 위해 객체 생성  
	
	private AccountService() {
		accountRepository = AccountRepository.getInstacne(); //서비스 호출시 레파지토리 인스턴스까지 호출 / 싱글톤이므로 결합도 낮음
	}
	
	public static AccountService getInstance() {
		
		if(instance == null) {
			instance = new AccountService();
		}
		return instance;
	}
	
	public int addAccount(Account account ) {
		return accountRepository.saveAccount(account); //2) 레파지토리로 //5) 받은 리턴 값을 AccountServlet으로 
		}
	
	//-3
	public Account getAccount(String username) {
		return accountRepository.findAccountByUsername(username);
	}
	
}
