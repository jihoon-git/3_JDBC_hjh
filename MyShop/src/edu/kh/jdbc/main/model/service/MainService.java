package edu.kh.jdbc.main.model.service;
import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.sql.Connection;

import edu.kh.jdbc.main.model.dao.MainDAO;
import edu.kh.jdbc.user.vo.User;

public class MainService {

	private MainDAO dao = new MainDAO();

	
	/** 아이디 중복 검사 서비스
	 * @param userId
	 * @return result
	 * @throws Exception
	 */
	public int idCheck(String userId) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.idCheck(conn, userId);
		
		close(conn);

		return result;
	}


	/** 회원 가입 서비스
	 * @param user
	 * @return result
	 * @throws Exception
	 */
	public int signUp(User user) throws Exception {

		Connection conn = getConnection();
		
		int result = dao.signUp(conn, user);
		
		if(result>0) commit(conn);
		else		rollback(conn);
		
		close(conn);
		
		return result;
	}


	/** 로그인 서비스
	 * @param userId
	 * @param userPw
	 * @return loginUser
	 * @throws Exception
	 */
	public User login(String userId, String userPw) throws Exception {
		Connection conn = getConnection();
		
		User loginUser = dao.login(conn, userId, userPw);
		
		close(conn);
		
		return loginUser;
	}
	
	
	
}
