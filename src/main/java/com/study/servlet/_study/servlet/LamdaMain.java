package com.study.servlet._study.servlet;

import java.util.function.Consumer;

import com.study.servlet._study.entity.Author;
import com.study.servlet._study.entity.Author.AuthorBuilder;

class Print<T>implements Consumer<T> {

	@Override
	public void accept(T t) {
		System.out.println(t);
	}
} //3)

public class LamdaMain {

	public static void main(String[] args) {
		Consumer<Author> print0 = new Print<Author>(); // 3)
		
		Consumer<Author> print1 = new Consumer<Author>() {
			
			@Override
			public void accept(Author author) {
				System.out.println(author);
			} 
		}; // 2)
		
		Consumer<Author> print2 = (author) -> System.out.println(author); // 람다-> 추상메소드는 무조건 하나, 실행x 정의o1)
		
		print0.accept(Author.builder().authorId(1).authorName("철수").build()); // 3)
		print1.accept(Author.builder().authorId(2).authorName("영희").build()); // 2)
		print2.accept(Author.builder().authorId(3).authorName("민수").build()); // 1)
		// 1) 2) 3) 셋 똑같음
		
		forEach(print2); 
		forEach(author -> {
			System.out.println("<<< test >>>");
			System.out.println(author);
			});
	}
	
	public static void forEach(Consumer<Author> action) {
		action.accept(Author.builder().authorId(4).authorName("민지").build());
	}

} 
