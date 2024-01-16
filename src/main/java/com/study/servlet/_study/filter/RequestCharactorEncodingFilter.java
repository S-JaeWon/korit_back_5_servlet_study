package com.study.servlet._study.filter;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;


@WebFilter("/*")
public class RequestCharactorEncodingFilter extends HttpFilter implements Filter {
       

    public RequestCharactorEncodingFilter() {
        super();

    }

	public void destroy() {
		
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		
		String[] methods = new String[] { "POST", "PUT" }; //배열 *대문자
		
		System.out.println(httpServletRequest.getMethod());
		
				//리스트(배열을)               //메소드(post put 메소드)  //무조건 대문자로	(소문자면 toLowerCase)			
		if(Arrays.asList(methods).contains(httpServletRequest.getMethod().toUpperCase())) {
			httpServletRequest.setCharacterEncoding("UTF-8");
		}
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
