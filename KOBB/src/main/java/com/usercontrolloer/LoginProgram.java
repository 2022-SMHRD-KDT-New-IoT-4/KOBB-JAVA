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

import com.google.gson.Gson;
import com.kobb.model.UserDAO;
import com.kobb.model.UserDTO;


public class LoginProgram implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//한글 인코딩
		request.setCharacterEncoding("UTF-8");
		System.out.println("값이 들어오니?");
		String result = null ;
				 
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		
		
		UserDTO dto = new UserDTO();
		
		dto.setUser_id(user_id);
		dto.setUser_pw(user_pw);
		
		UserDAO dao = new UserDAO();
		
		System.out.println(dto.getUser_id());
		System.out.println(dto.getUser_pw());
		
		
		
		
		
		UserDTO row = dao.login(dto);
		System.out.println(row);
		
		
		
		
		
		if(row != null) {		
			System.out.println("성공" + row.getUser_shop_name());
			OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
			
			//아이디 , 패스워드. 이름
			writer.append(row.getUser_id());
			writer.append(",");
			writer.append(row.getUser_pw());
			//온오프 제어 값
			writer.append(".");
			writer.append(row.getUser_shop_name());
			
			writer.flush(); // 100,12345
			writer.close();
			
		
			
		}else {
			System.out.println("실패");
			response.getWriter().print("fuck");
			result = "로그인실패";
		}
		
			
	
		return result;
	}

}
