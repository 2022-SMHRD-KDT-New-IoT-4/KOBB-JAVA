package com.buttoncontoller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kobb.model.ButtonDAO;
import com.kobb.model.ButtonDTO;
import com.usercontrolloer.Command;

	

public class buttonProgram implements Command {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		
		//아두이노에서 버튼 값 이
		// 아두이노 요청을 읽어오는 구간
		String bodyJson = "";

		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader br = null; //

		try {
			String line = "";
			// body내용 inputstream에 담는다.
			InputStream inputStream = request.getInputStream();
			if (inputStream != null) {
				br = new BufferedReader(new InputStreamReader(inputStream));
				// 더 읽을 라인이 없을때까지 계속
				while ((line = br.readLine()) != null) {
					stringBuilder.append(line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 읽어온 데이터를 json으로 파싱하는 구간
		Gson gson = new Gson();
		ButtonDTO inputState = gson.fromJson(stringBuilder.toString(), ButtonDTO.class);

		System.out.println("버튼test" + stringBuilder.toString());
		ButtonDTO dto = new ButtonDTO();
		ButtonDAO dao = new ButtonDAO();

		ButtonDTO query = new ButtonDTO();
		query.setSerial_no(inputState.getSerial_no());
		//Serial_no :  select로 값을 받아와 현재 led들의 정보를 확인 
		ButtonDTO lastState = dao.ButtonSelect(query);

//		if (lastState == null) {
//			System.out.println(inputState.getSerial_no());
//
//			return;
//		}

		boolean updateFlag = false;
		//현재 led의 값 확인
		updateFlag = updateFlag || (inputState.getBtn1() != lastState.getBtn1());
		updateFlag = updateFlag || (inputState.getBtn2() != lastState.getBtn2());
		updateFlag = updateFlag || (inputState.getBtn3() != lastState.getBtn3());
		updateFlag = updateFlag || (inputState.getBtn4() != lastState.getBtn4());
		updateFlag = updateFlag || (inputState.getBtn5() != lastState.getBtn5());
		//조건을 줘서 led값 변경 
		if (updateFlag) {
			System.out.println(updateFlag);
			int row = dao.ButtonUpdate(inputState);
			if (row > 0) {
				System.out.println("업데이트 성공");
				
			} else {
				System.out.println("업데이트 실패");
			}
		}

		System.out.println(stringBuilder.toString());
		
//       System.out.println(gson.fromJson(stringBuilder.toString(), LedCountDTO.class));
//       System.out.println("redled: " + dto.getRed_led() +  ", orangeled: " + dto.getOrange_led() + ", greenled: " + dto.getGreen_led()+" , serial :"+dto.getSerial_no());
			
			return bodyJson;
	}
}
