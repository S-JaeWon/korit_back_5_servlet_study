package com.study.servlet._study.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Author {
	private int authorId;
	private String authorName;
}
