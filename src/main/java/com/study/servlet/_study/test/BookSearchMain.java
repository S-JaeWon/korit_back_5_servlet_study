package com.study.servlet._study.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.study.servlet._study.config.DBConnectionMgr;
import com.study.servlet._study.entity.Author;
import com.study.servlet._study.entity.Book;
import com.study.servlet._study.entity.Publisher;

public class BookSearchMain {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("도서명 검색 >>> ");
		String name = scanner.nextLine();
		// 검색할 도서명을 입력하세요 >>> 글
		
		// 도서명 / 저자명 / 출판사
		
		DBConnectionMgr pool = DBConnectionMgr.getInstance(); 
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Book> bookList = new ArrayList<>();
		
		try {
			con = pool.getConnection();
			
			String sql = "select\r\n"
					+ "	bt.book_id,\r\n"
					+ "    bt.book_name,\r\n"
					+ "    at.author_id,\r\n"
					+ "    at.author_name,\r\n"
					+ "    pt.publisher_id,\r\n"
					+ "    pt.publisher_name\r\n"
					+ "from\r\n"
					+ "	book_tb bt\r\n"
					+ "    left outer join author_tb at on(at.author_id = bt.author_id)\r\n"
					+ "    left outer join publisher_tb pt on(pt.publisher_id = bt.publisher_id)\r\n"
					+ "where\r\n"
					+ "	bt.book_name like ?;";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				/*Author author = Author.builder()
						.authorId(rs.getInt(3))
						.authorName(rs.getString(4))
						.build();*/
				/*Publisher publisher = Publisher.builder()
						.publisherId(rs.getInt(5))
						.publisherName(rs.getString(6))
						.build();*/
				/*Book book = Book.builder()
						.bookId(rs.getInt(1))
						.bookName(rs.getString(2))
						.author(Author)
						.publisher(Publisher)
						.build();*/
				/*bookList.add(book);*/
				
				bookList.add(Book.builder()
						.bookId(rs.getInt(1))
						.bookName(rs.getString(2))
						.author(Author.builder().authorId(rs.getInt(3)).authorName(rs.getString(4)).build())
			            .publisher(Publisher.builder().publisherId(rs.getInt(5)).publisherName(rs.getString(6)).build())
			            .build());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt,rs);
		}
		// bookList.forEach(book -> System.out.println(book));
		System.out.println("도서명 / 저자명 / 출판사");
		for(Book book : bookList) {
			
			System.out.println(book.getBookName() + " / " + book.getAuthor().getAuthorName() + " / " + book.getPublisher().getPublisherName());
		}
	}

}
