package edu.kh.jdbc.user.model.service;

import edu.kh.jdbc.user.model.dao.UserDAO;
import edu.kh.jdbc.user.vo.User;

import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.sql.Connection;

public class UserService {
	
	private UserDAO dao = new UserDAO();

	/** 회원 정보 수정 서비스(전화번호, 주소)
	 * @param user
	 * @return result
	 * @throws Exception
	 */
	public int updateUser(User user) throws Exception {

		Connection conn = getConnection();
		int result = dao.updateUser(conn, user);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	/** 비밀번호 변경 서비스
	 * @param currentPw
	 * @param newPw1
	 * @param userNo
	 * @return result
	 * @throws Exception
	 */
	public int updatePw(String currentPw, String newPw1, int userNo) throws Exception {

		Connection conn = getConnection();
		
		int result = dao.updateUser(conn, currentPw, newPw1, userNo);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	/** 회원 탈퇴 서비스
	 * @param userPw
	 * @param userNo
	 * @return result
	 * @throws Exception
	 */
	public int secession(String userPw, int userNo) throws Exception {

		Connection conn = getConnection();
		
		int result = dao.secession(conn, userPw, userNo);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
	
		return result;
	}
	
	
	

}
