package com.study.servlet._study.repository;

import java.util.ArrayList;
import java.util.List;

import com.study.servlet._study.entity.Account;

public class AccountRepository {
	//리스트는 공유 되어야 하고 레파지토리에 있으므로 싱글톤 사용 
	
	private List<Account> accounList;
	
	private static AccountRepository instance; //1) 자기 자신의 자료형을 담을 수 있는 인스턴스 객체 생성 
	
	/*
	 * public AccountRepository() { accounList = new ArrayList<>(); }
	 */
	
	private AccountRepository() {
		accounList = new ArrayList<>();
	} //2) 외부에서 객채 생성 막기
	
	public static AccountRepository getInstacne() {
		if(instance == null) {
			instance = new AccountRepository();
		}
		return instance;
	} //3) instance 가 null 일 때만 생성
	
	public int saveAccount(Account account) {
		accounList.add(account); //3) 리스트에 추가 
		return 1; //4)서비스로 
	}
	
	// -2
	public Account findAccountByUsername(String username) {
		Account findAccount = null;
		
		for(Account account : accounList) {
			if(account.getUsername().equals(username)) {
				findAccount = account; 
				break;
			}
		}
		return findAccount;
	}
}










