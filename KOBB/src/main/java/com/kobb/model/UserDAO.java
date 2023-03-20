package com.kobb.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionManager;

public class UserDAO {
		private SqlSessionFactory sqlSessionFactory = com.db.SqlSessionManager.getSqlSession();
		
		
		// 회원가입
		public int join(UserDTO dto) {
			int row = 0;
			SqlSession session = sqlSessionFactory.openSession(true);
			try {
				row = session.insert("com.kobb.model.UserDAO.join", dto);
				
			}catch (Exception e) {
				e.printStackTrace();
			} finally {
				session.close();
			}
			return row;
			
			
		}
		
		//로그인 
				public UserDTO login(UserDTO dto) {
					UserDTO row = null;
					SqlSession sqlSession = sqlSessionFactory.openSession(true);
					try {
						row = sqlSession.selectOne("com.kobb.model.UserDAO.login", dto);
						System.out.println(row);
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						sqlSession.close();
					}
					return row;
					
				}
				
		// 메뉴 조회 (selectAll)
				public List<MenuDTO> menu(MenuDTO dto) {
					List<MenuDTO> list = null;
					SqlSession sqlSession = sqlSessionFactory.openSession(true);
					try {
						list = sqlSession.selectList("com.kobb.model.UserDAO.menu",dto);
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						sqlSession.close();
					}
					return list;
	
					
				}


	

}
