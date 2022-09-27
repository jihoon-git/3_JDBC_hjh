package edu.kh.jdbc.order.model.service;

import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import edu.kh.jdbc.order.model.dao.OrderDAO;
import edu.kh.jdbc.order.vo.Order;

public class OrderService {
	
	private OrderDAO dao = new OrderDAO();

	
	/** 전체 상품 목록 조회 서비스
	 * @return oederList
	 * @throws Exception
	 */
	public List<Order> selectProduct() throws Exception{
		Connection conn = getConnection();
		
		List<Order> orderList = dao.selectProduct(conn);
		
		close(conn);
		
		return orderList;
	}


	/** 상품 주문하기 서비스
	 * @param order
	 * @return 
	 * @throws Exception
	 */
	public int insertOrder(Order order) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.insertOrder(conn, order);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	/** 주문 취소 서비스
	 * @param orderNo
	 * @param userNo
	 * @return result
	 * @throws Exception
	 */
	public int withdrawOrder(int orderNo, int userNo) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.secession(conn, orderNo, userNo);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	
	/** 내 주문 내역 조회 서비스
	 * @return
	 * @throws Exception
	 */
	public List<Order> selectMyOrder() throws Exception{
		Connection conn = getConnection();
		
		List<Order> orderList = dao.selectMyOrder(conn);
		
		close(conn);
		
		return orderList;
	}



	

}
