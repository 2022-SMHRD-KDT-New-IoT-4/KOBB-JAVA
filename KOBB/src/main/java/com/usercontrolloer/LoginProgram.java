package com.usercontrolloer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.kobb.model.UserDAO;
import com.kobb.model.UserDTO;


public class LoginProgram implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Json 타입으로 키값과 함께 넘겨주기 위해 필요한 객채 생성 --> pom.xml 에 depen있어야함!
		ObjectMapper objectMapper = new ObjectMapper();
		
		// 여기 result 는 null 값이여야만 함!~
		String result =null;
				 
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		String user_shop_name = request.getParameter("user_shop_name");
		request.setCharacterEncoding("UTF-8");
		
		
		UserDTO dto = new UserDTO();
		
		
		dto.setUser_id(user_id);
		dto.setUser_pw(user_pw);
		dto.setUser_shop_name(user_shop_name);
		UserDAO dao = new UserDAO();
		
		UserDTO row = dao.login(dto);
		
		
		
		if(row != null) {		
			
			//아이디 , 패스워드 . 매장이름
			response.setContentType("application/json; charset=UTF-8");
			response.setContentType("charset=UTF-8");
			String user_info = objectMapper.writeValueAsString(row);
			response.getWriter().print(user_info);
			System.out.println("조회된 로그인 정보 :"+ user_info);
	
			
			
		
			
		}else {
			System.out.println("실패");
			response.getWriter().print("로그인 실패");
			result = "로그인실패";
		}
	
		
			
	
		return result;
	}

}
