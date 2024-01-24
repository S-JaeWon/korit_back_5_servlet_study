package com.study.servlet._study.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.study.servlet._study.config.DBConnectionMgr;
import com.study.servlet._study.entity.Author;
import com.study.servlet._study.entity.Book;
import com.study.servlet._study.entity.Publisher;

public class BookInsertMain {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String bookname = null;
		String authorName = null;
		String publisherName= null;
		
		System.out.print("도서명 >>> ");
		bookname = scanner.nextLine();
		System.out.print("저자명 >>> ");
		authorName = scanner.nextLine();
		System.out.print("출판사 >>> ");
		publisherName = scanner.nextLine();
		
		Book book = Book.builder()
					.bookName(bookname)
					.author(Author.builder().authorName(authorName).build())
					.publisher(Publisher.builder().publisherName(publisherName).build())
					.build();
		
		DBConnectionMgr pool = DBConnectionMgr.getInstance();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		// ResultSet rs = null; -> 결과 값이 없어서 생략
		
		int count = 0;
		
		try { 
			con = pool.getConnection();
			String sql = "insert into author_tb values (0, ?)";
			pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // R.G.KEYS, AI로 인해 증가한 값을 갖고 오기 위해 써줌
			pstmt.setString(1, book.getAuthor().getAuthorName());
			pstmt.executeUpdate(); // insert update delete 할때 사용 
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				book.getAuthor().setAuthorId(rs.getInt(1)); // book 객체 안에 있는 author 객체를 꺼내서 author 객체 안에 값 대입
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		
		try { 
			con = pool.getConnection();
			String sql = "insert into publisher_tb values (0, ?)";
			pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // R.G.KEYS, AI로 인해 증가한 값을 갖고 오기 위해 써줌
			pstmt.setString(1, book.getPublisher().getPublisherName());
			pstmt.executeUpdate(); // insert update delete 할때 사용 
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				book.getPublisher().setPublisherId(rs.getInt(1)); 
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		
		try { 
			con = pool.getConnection();
			String sql = "insert into book_tb values (0, ?, ?, ?)"; // bookId, AuthorId, PublisherId
			pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // R.G.KEYS, AI로 인해 증가한 값을 갖고 오기 위해 써줌
			pstmt.setString(1, book.getBookName());
			pstmt.setInt(2, book.getAuthor().getAuthorId());
			pstmt.setInt(3, book.getPublisher().getPublisherId());
			pstmt.executeUpdate(); // insert update delete 할때 사용 
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				book.setBookId(rs.getInt(1)); 
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		
		System.out.println("추가된 도서 정보");
		System.out.println(book);
		
	}

}
