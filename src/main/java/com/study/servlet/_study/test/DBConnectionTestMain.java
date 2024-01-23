package com.study.servlet._study.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.xdevapi.PreparableStatement;
import com.study.servlet._study.config.DBConnectionMgr;
import com.study.servlet._study.entity.Author;

public class DBConnectionTestMain {

	public static void main(String[] args) {
		DBConnectionMgr pool = DBConnectionMgr.getInstance(); 
		// 이탤릭체 -> static, getInstace은 보통 싱글톤, 메소드는 생성자가 필요하므로 여기서 pool 생성 
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 전역 변수, try 문과 fianlly 문 각각 들어갈 수 있음.
		
		// DB 접속시 예외처리
		try {
			con = pool.getConnection();
			String name = "철수"; // 아래 sql 실행문에 일일이 '' 쓸 수 없으므로 따로 빼둠
			String sql = "select * from author_tb where author_name ?"; // 실행하고자 하는 명령문 
			pstmt = con.prepareStatement(sql); // 쿼리창 생성, String sql 에 들어간 명령문 대입
			pstmt.setString(1, name); // 괄호 안 숫자는 ? 순서에 기입
			
			rs = pstmt.executeQuery(); // 실행 
			
			List<Author> authorList = new ArrayList<>();
			
			//각 리스트에 id 값과 name 값을 넣음
			while(rs.next()) {
				authorList.add(Author.builder()
						.authorId(rs.getInt(1))
						.authorName(rs.getString(2))
						.build());
			}
			// forEach문을 통해 각 리스트를 출력함.
			authorList.forEach(author -> System.out.println(author)); // 중간에 멈출 수 없어서 멈추려면 조건식 필요 -> 그럴 경우 for문 사용
			
			for(Author author : authorList) {
				System.out.println(author);
			}
			
			for(int i = 0; i < authorList.size(); i++) {
				Author author = authorList.get(i);
				System.out.println(author);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt,rs);
		}
	}

}
