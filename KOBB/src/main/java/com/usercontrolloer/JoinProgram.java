package com.usercontrolloer;

import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.filters.SetCharacterEncodingFilter;

import com.kobb.model.UserDAO;
import com.kobb.model.UserDTO;


public class JoinProgram implements Command {

	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("서버 요청이 들어옴!");
		// 여기 result 는 null 값이여야만 함!~
		String result = null;

		
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		String user_phone = request.getParameter("user_phone");
		String user_shop_name = request.getParameter("user_shop_name");
		response.setCharacterEncoding("UTF-8");
		
		
		System.out.println(user_shop_name);
		System.out.println(user_id);
		System.out.println(user_pw);
		System.out.println(user_phone);
		
		UserDTO dto = new UserDTO(user_id,user_pw,user_phone,user_shop_name);
		UserDAO dao = new UserDAO();
		
		int row = dao.join(dto);
		if(row>0) {
			// 정보 담을 객체 가져오기
			//OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
			response.setContentType("application/json; charset=UTF-8");
			response.setContentType("charset=UTF-8");
			response.getWriter().print(user_shop_name);
//			writer.append(user_shop_name);
//		    
//			System.out.println(user_shop_name);
//			writer.flush();
//			writer.close();
			
			
			
			// 성공 시 어디로 넘어가지?
			// result;
		}else {
			result = "아이디 중복";
		}
		
		
		
		return result;
	}

}
