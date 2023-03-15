package com.kobb.model;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.db.SqlSessionManager;
import com.kobb.model.ButtonDTO;


public class ButtonDAO {
	private SqlSessionFactory sqlSessionFactory = SqlSessionManager.getSqlSession();
	
	//시리얼 넘버를 활용해서 현재 버튼값 확인
	public ButtonDTO ButtonSelect(ButtonDTO dto) {
		ButtonDTO row = null;
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		try {
			row = sqlSession.selectOne("com.kobb.model.ButtonDAO.ButtonSelect", dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return row;
	}

	
	// 아두이노 버튼 값
		public int ButtonUpdate(ButtonDTO dto) {
			int row = 0;
			SqlSession sqlSession = sqlSessionFactory.openSession(true);
			try {
				row = sqlSession.update("com.kobb.model.ButtonDAO.ButtonUpdate", dto);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				sqlSession.close();
			}
			return row;
		}
}
