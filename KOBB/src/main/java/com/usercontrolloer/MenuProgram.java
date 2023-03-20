package com.usercontrolloer;

import java.awt.datatransfer.Clipboard;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.mapper.Mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kobb.model.MenuDTO;
import com.kobb.model.UserDAO;
import com.kobb.model.UserDTO;

public class MenuProgram implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		//한글 인코딩
		// 여기 result 는 null 값이여야만 함!
		String result =null;
				 
		String user_id = request.getParameter("user_id");
		request.setCharacterEncoding("UTF-8");
		
		UserDAO dao = new UserDAO();
		UserDTO user_dto = new UserDTO();
		
		MenuDTO menu_dto = new MenuDTO();
		
	    menu_dto.setUser_id(user_id);
	    
	    
		List<MenuDTO> menus = dao.menu(menu_dto);
		
		
		
		
		if(menus != null) {
			response.setContentType("application/json; charset=UTF-8");
			response.setContentType("charset=UTF-8");
			String menu_info = objectMapper.writeValueAsString(menus);
			response.getWriter().print(menu_info);
			System.out.println("조회된 메뉴 목록: "+menu_info);
			
			
		}else {
			System.out.println("실패");
			response.getWriter().print("로그인 실패");
			result = "실패";
		}
		
		return result;
	}
	
	
	

}
