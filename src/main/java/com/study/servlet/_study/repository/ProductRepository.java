package com.study.servlet._study.repository;

import java.util.ArrayList;
import java.util.List;

import com.study.servlet._study.entity.Product;

public class ProductRepository {
	
	private List<Product> productList;
	
	private static ProductRepository instance;
	
	private ProductRepository() {
		productList = new ArrayList<>();
	}
	
	public static ProductRepository getInstace() {
		if(instance == null) {
			instance = new ProductRepository();
		}
		return instance;
		
	}
	
	public int saveProduct(Product product) {
		productList.add(product);  
		return 1; 
	}
	
	public Product findProductByProductname(String productname) {
		Product findProduct = null;
		
		for(Product product : productList) {
			if(product.getProductname().equals(productname)) {
				findProduct = product; 
				break;
			}
		}
		return findProduct;
	}
}
