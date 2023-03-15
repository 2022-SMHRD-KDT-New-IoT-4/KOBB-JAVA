package com.buttoncontoller;

import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kobb.model.ButtonDAO;
import com.kobb.model.ButtonDTO;
import com.usercontrolloer.Command;

public class buttonAndroid implements Command{
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//한글 인코딩
		request.setCharacterEncoding("UTF-8");
		//System.out.println("버튼 들어오니???");
		String result = null ;
				 
		String serial_no = request.getParameter("serial_no");
	
		
		
		ButtonDTO dto = new ButtonDTO();
		
		dto.setSerial_no(serial_no);
	
		
		ButtonDAO dao = new ButtonDAO();
		
//		System.out.println(dto.getUser_id());
//		System.out.println(dto.getUser_pw());
		
		ButtonDTO row = dao.ButtonSelect(dto);
	//	System.out.println(row);
		
		
		if(row != null) {		
			//System.out.println("성공" + row.getSerial_no());
			OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
			
			//버튼 값 
			writer.append(row.getBtn1());
			writer.append(",");
			writer.append(row.getBtn2());
			writer.append(".");
			writer.append(row.getBtn3());
			writer.append("/");
			writer.append(row.getBtn4());
			writer.append("_");
			writer.append(row.getBtn5());
			
			writer.flush(); // 100,12345
			writer.close();
			
		
			
		}else {
			System.out.println("실패");
			response.getWriter().print("fuck");
			result = "값 넘기기 실패";
		}
		
			
	
		return result;
	}

}
