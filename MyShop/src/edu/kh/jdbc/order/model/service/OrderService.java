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

	/** 주문 수량 변경 서비스
	 * @param order 
	 * @return result
	 * @throws Exception
	 */
	public int updateQuantity(Order order) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.updateQuantity(conn, order);
		
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
		
		int result = dao.withdrawOrder(conn, orderNo, userNo);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}
	

	/** 내 주문 내역 조회 서비스
	 * @param userNo
	 * @return orderList
	 * @throws Exception
	 */
	public List<Order> selectMyOrder(int userNo) throws Exception {
		Connection conn = getConnection();
		
		List<Order> orderList = dao.selectMyOrder(conn, userNo);
		
		close(conn);
		
		return orderList;
	}


	/** 내 취소 내역 조회 서비스
	 * @param userNo
	 * @return withdrawList
	 * @throws Exception
	 */
	public List<Order> selectWithdraw(int userNo) throws Exception {
		Connection conn = getConnection();
		
		List<Order> withdrawList = dao.selectWithdraw(conn, userNo);
		
		close(conn);
		
		return withdrawList;
	}


	/** 결제완료 상태(수량변경/취소 가능)인 주문 목록 조회 서비스
	 * @param userNo
	 * @return ingOrderList
	 * @throws Exception
	 */
	public List<Order> ingOrder(int userNo) throws Exception {
		Connection conn = getConnection();
		
		List<Order> ingOrderList = dao.ingOrder(conn, userNo);
		
		close(conn);
		
		return ingOrderList;
	}


	

}
