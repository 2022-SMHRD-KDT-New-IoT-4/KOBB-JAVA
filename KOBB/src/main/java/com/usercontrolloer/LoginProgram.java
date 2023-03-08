package com.usercontrolloer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.kobb.model.UserDAO;
import com.kobb.model.UserDTO;

public class LoginProgram implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("값이 들어오니?");
		// 여기 result 는 null 값이여야만 함!~
		String result =null;
				 
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		
		
		UserDTO dto = new UserDTO();
		
		dto.setUser_id(user_id);
		dto.setUser_pw(user_pw);
		
		UserDAO dao = new UserDAO();
		
		UserDTO row = dao.login(dto);
		
		if(row != null) {
			request.getSession().setAttribute("Login_page", row);
			
		}
	
		
	
		return result;
	}

}
