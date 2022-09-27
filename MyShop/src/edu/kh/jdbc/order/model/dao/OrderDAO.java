package edu.kh.jdbc.order.model.dao;

import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.jdbc.order.vo.Order;

public class OrderDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;
	
	public OrderDAO() {
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("order-query.xml"));
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/** 전체 상품 목록 조회 DAO
	 * @param conn
	 * @return orderList
	 * @throws Exception
	 */
	public List<Order> selectProduct(Connection conn) throws Exception {

		List<Order> orderList = new ArrayList<>();
		
		try {
			String sql = prop.getProperty("selectProduct");
			
			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				int snackNo = rs.getInt("SNACK_NO");
				String snackName = rs.getString("SNACK_NAME");
				int singlePrice = rs.getInt("PRICE");
				
				Order order = new Order();
				order.setSnackNo(snackNo);
				order.setSnackName(snackName);
				order.setSinglePrice(singlePrice);
				
				orderList.add(order);
			}
		} finally {
			close(rs);
			close(stmt);
		}
		return orderList;
	}

	/** 상품 주문하기 DAO
	 * @param conn
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public int insertOrder(Connection conn, Order order) throws Exception{
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("insertOrder");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, order.getUserNo());
			pstmt.setInt(2, order.getSnackNo());
			pstmt.setInt(3, order.getQuantity());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 주문 수량 변경 DAO
	 * @param conn
	 * @param order
	 * @return result
	 * @throws Exception
	 */
	public int updateQuantity(Connection conn, Order order) throws Exception {
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("updateQuantity");
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, order.getQuantity());
			pstmt.setInt(2, order.getOrderNo());
			pstmt.setInt(3, order.getOrderNo());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	/** 주문 취소 DAO
	 * @param conn
	 * @param orderNo
	 * @param userNo
	 * @return result
	 * @throws Exception
	 */
	public int withdrawOrder(Connection conn, int orderNo, int userNo) throws Exception {
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("withdrawOrder");
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, orderNo);
			pstmt.setInt(2, userNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	/** 내 주문 내역 조회 DAO
	 * @param conn
	 * @return orderList
	 * @throws Exception
	 */
	public List<Order> selectMyOrder(Connection conn) throws Exception {

		List<Order> orderList = new ArrayList<>();
		
		try {

			String sql = prop.getProperty("selectMyOrder");
			pstmt = conn.prepareStatement(sql);
			
//			pstmt.setInt(1, userNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				Order order = new Order();
				order.setOrderNo(rs.getInt("ORDER_NO"));
				
				orderList.add(order);
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		return orderList;
	}




	
	
	
	
}
