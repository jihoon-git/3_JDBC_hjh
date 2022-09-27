package edu.kh.jdbc.user.model.dao;
import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import edu.kh.jdbc.user.vo.User;

public class UserDAO {
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;
	
	public UserDAO() {
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("user-query.xml"));
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/** 내 회원 정보 수정 DAO (전화번호, 주소)
	 * @param conn
	 * @param user
	 * @return result
	 * @throws Exception
	 */
	public int updateUser(Connection conn, User user) throws Exception {

		int result = 0;
		try {
			String sql = prop.getProperty("updateUser");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, user.getUserPhone());
			pstmt.setString(2, user.getUserAddress());
			pstmt.setInt(3, user.getUserNo());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
	}

	/** 비밀번호 변경 DAO
	 * @param conn
	 * @param currentPw
	 * @param newPw1
	 * @param userNo
	 * @return result
	 * @throws Exception
	 */
	public int updateUser(Connection conn, String currentPw, String newPw1, int userNo) throws Exception{
		int result = 0;
		try {
			String sql = prop.getProperty("updatePw");

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, newPw1);
			pstmt.setInt(2, userNo);
			pstmt.setString(3, currentPw);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
	}

	/** 회원 탈퇴 DAO
	 * @param conn
	 * @param userPw
	 * @param userNo
	 * @return result 
	 * @throws Exception
	 */
	public int secession(Connection conn, String userPw, int userNo) throws Exception{
		int result = 0;
		try {
			String sql = prop.getProperty("secession");

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, userNo);
			pstmt.setString(2, userPw);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	

}
