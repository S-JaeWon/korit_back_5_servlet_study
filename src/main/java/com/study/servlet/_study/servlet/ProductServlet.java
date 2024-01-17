package com.study.servlet._study.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.servlet._study.entity.Product;
import com.study.servlet._study.service.ProductService;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService;
       
    public ProductServlet() {
        super();
        productService = ProductService.getInstance();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productname = request.getParameter("productname");
		
		Product product = productService.getProduct(productname);
		response.setStatus(200);
		response.getWriter().println(product);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int price = 0;
		
		try {
			price = Integer.parseInt(request.getParameter("price"));
		} catch (NumberFormatException e) {
			response.setStatus(400);
			response.getWriter().println("숫자만 입력해야 합니다.");
			return;
		}
		
		/*		String productname = request.getParameter("productname"); 
				String price =
				request.getParameter("price"); 
				String size = request.getParameter("size");
				String color = request.getParameter("color");*/
		
		Product product = Product.builder()
				.productname(request.getParameter("productname"))
				.price(price)
				.size(request.getParameter("size"))
				.color(request.getParameter("color"))
				.build();
		//중복확인
		if(productService.getProduct(product.getProductname()) != null) {
			response.setStatus(400);
			response.getWriter().println("이미 등록된 상품명입니다.");
			return;
		}
		
		productService.addProduct(product);
		response.setStatus(201);
		response.getWriter().println("상품 등록이 완료되었습니다.");
		
		
//		int body = productService.addProduct(product); 
//		response.setStatus(201);
//		response.getWriter().println(body);
		 
	}

}
/*
 * product
 * 			producName	= 티셔츠
 * 			price		= 20000
 * 			size		= M
 * 			color		= black
 * 
 * ProductServlet
 * 			doGet
 * 			doPost
 * 
 * ProductService
 * 
 * ProductRepository
 * 			productList
 */