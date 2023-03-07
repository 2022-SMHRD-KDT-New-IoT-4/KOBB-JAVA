package com.usercontrolloer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.kobb.model.UserDAO;
import com.kobb.model.UserDTO;


public class JoinProgram implements Command {

	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("서버 요청이 들어옴!");
		String result = null;

		String user_shop_name = request.getParameter("user_shop_name");
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		String user_phone = request.getParameter("user_phone");
		
		
		System.out.println(user_shop_name);
		System.out.println(user_id);
		System.out.println(user_pw);
		System.out.println(user_phone);
		UserDTO dto = new UserDTO(user_shop_name,user_id,user_pw,user_phone);
		UserDAO dao = new UserDAO();
		
		int row = dao.join(dto);
		if(row>0) {
			request.getSession().setAttribute("user_shop_name", dao);
			// 성공 시 어디로 넘어가지?
			result = "Join.do";
		}else {
			// 실패 시는 ?
			// result  "";
		}
		
		
		
		return result;
	}

}
