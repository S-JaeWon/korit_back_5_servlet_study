package com.study.servlet._study.servlet;

import java.io.IOException;
import java.security.KeyStore.Entry;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http. HttpServletResponse;

import com.study.servlet._study.utils.ParamsConverter;

@WebServlet("/http")
public class HttpStudyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public HttpStudyServlet() {
        super();
    }
    
    // HTTP 메소드
    // POST 요청 	-> C reate(추가)
    // GET 요청 	-> R ead(조회)
    // PUT 요청		-> U pdate(수정)
    // DELETE 요청	-> D elete(삭제)
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Map<String, String[]> map = request.getParameterMap();
    	//request.setCharacterEncoding("UTF-8"); //post, body에서 요청 할때마다 인코딩 필요
    	//body에 키, 값 입력
		//Map, Map1 -> 값 출력 하는 방법 -> 힘들면 String NameParams = request.getParameter("name"); String phoneParams = 에 하나씩 값 입력
    	Map<String, String> paramsMap = new HashMap<>();
		
		
		request.getParameterMap().forEach((k, v) -> {
			//String[] valueArray = v;
			StringBuilder builder = new StringBuilder();
			
			Arrays.asList(v).forEach(value -> builder.append(value));
			
			System.out.println(k + ": " + builder.toString());
			/*for(String value : v) {
				System.out.print(value);
			}
			System.out.println();*/
			paramsMap.put(k, builder.toString());		//배열에 있는 값들을 반복을 통해 문자열로 하나씩 차곡 차곡 붙여서 출력
		});
		
		System.out.println(paramsMap);
		//System.out.println(request.getParameter("name"));
		
		Map<String, String> paramsMap2 = new HashMap<>();
		Iterator<String> ir = request.getParameterNames().asIterator();
		while(ir.hasNext()) {
			String key = ir.next();
			paramsMap2.put(key, request.getParameter(key));
			//System.out.println(ir.next());
		}
		
		/*
		 * String NameParams = request.getParameter("name"); String phoneParams =
		 * request.getParameter("phone"); String emailParams =
		 * request.getParameter("email"); String addressParams =
		 * request.getParameter("address");
		 * 
		 * System.out.println(NameParams); System.out.println(phoneParams);
		 * System.out.println(emailParams); System.out.println(addressParams);
		 */
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Params에 키, 값 입력. 주소값에 키, 값 추가됨 / 주소창에 값이 다 나오므로 로그인에 부적합
		//static method를 이용하면 주소에 값 표시 안 하고 리턴 바로 가능
		/*
		 * Map<String, String> paramsMap = new HashMap<>();
		 * 
		 * request.getParameterMap().forEach((k, v) -> { StringBuilder builder = new
		 * StringBuilder();
		 * 
		 * Arrays.asList(v).forEach(value -> builder.append(value));
		 * 
		 * //System.out.println(k + ": " + builder.toString());
		 * 
		 * paramsMap.put(k, builder.toString()); });
		 */
		
		Map<String, String> paramsMap = ParamsConverter.convertParamsMapToMap(request.getParameterMap());
		
		System.out.println(paramsMap);
	}
	
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

}
