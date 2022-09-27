package edu.kh.jdbc.main.model.dao;
import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import edu.kh.jdbc.user.vo.User;


public class MainDAO {
	
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private Properties prop = null;
	
	public MainDAO() {
	
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("main-query.xml"));
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/** 아이디 중복 검사 DAO
	 * @param conn
	 * @param userId
	 * @return result
	 * @throws Exception
	 */
	public int idCheck(Connection conn, String userId) throws Exception {
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("idCheck");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt("COUNT(*)");
			}
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}

	/** 회원가입 DAO
	 * @param conn
	 * @param user
	 * @return result
	 * @throws Exception
	 */
	public int signUp(Connection conn, User user) throws Exception {
		int result = 0;
		try {
			String sql = prop.getProperty("signUp");
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserPw());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getUserPhone());
			pstmt.setString(5, user.getUserAddress());

			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);	
		}
		return result;
	}

	/** 로그인 DAO
	 * @param conn
	 * @param userId
	 * @param userPw
	 * @return loginUser
	 * @throws Exception
	 */
	public User login(Connection conn, String userId, String userPw) throws Exception {
		
		User loginUser = null;
		
		try {
			
			String sql = prop.getProperty("login");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			pstmt.setString(2, userPw);
						
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				loginUser = new User(rs.getInt("USER_NO")
						, userId, userPw,
						rs.getString("USER_NAME"),
						rs.getString("USER_PHONE"),
						rs.getString("USER_ADDR"),
						rs.getString("ENROLL_DATE"));
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		return loginUser;
	}

	
	
	
}
