package com.study.servlet._study.service;

import com.study.servlet._study.entity.Product;
import com.study.servlet._study.repository.ProductRepository;

public class ProductService {
	
	private static ProductService instance;
	
	private ProductRepository productRepository;
	
	private ProductService() {
		productRepository = ProductRepository.getInstace();
	}
	
	public static ProductService getInstance() {
			
			if(instance == null) {
				instance = new ProductService();
			}
			return instance;
		}
	
	public int addProduct(Product product) {
		return productRepository.saveProduct(product);
	}
	
	public Product getProduct(String productname) {
		return productRepository.findProductByProductname(productname);
	}

}
