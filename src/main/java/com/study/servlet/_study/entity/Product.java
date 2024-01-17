package com.study.servlet._study.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Product {
	private String productname;
	private int price;
	private String size;
	private String color;
}
