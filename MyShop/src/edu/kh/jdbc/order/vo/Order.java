package edu.kh.jdbc.order.vo;

import java.util.List;

public class Order {

	
	private int orderNo; // 주문번호
	private int userNo; // 구매자 회원번호
	private String userName; //구매자 이름
	private String orderDate; // 주문 날짜
	private int snackNo; //상품 번호
	private String snackName; // 상품명
	private int singlePrice; //단품 가격
	private int quantity; //주문수량
	private int price; //주문 금액
	private String shipping; // 배송 상태
	private String withraw; // 취소 상태
	
	public Order() { }
	
	

	public Order(int snackNo, String snackName, int singlePrice) {
		super();
		this.snackNo = snackNo;
		this.snackName = snackName;
		this.singlePrice = singlePrice;
	}



	public Order(int orderNo, int userNo, String userName, String orderDate, String snackName, int quantity, int price,
			String shipping, String withraw) {
		super();
		this.orderNo = orderNo;
		this.userNo = userNo;
		this.userName = userName;
		this.orderDate = orderDate;
		this.snackName = snackName;
		this.quantity = quantity;
		this.price = price;
		this.shipping = shipping;
		this.withraw = withraw;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public int getSnackNo() {
		return snackNo;
	}

	public void setSnackNo(int snackNo) {
		this.snackNo = snackNo;
	}

	public String getSnackName() {
		return snackName;
	}

	public void setSnackName(String snackName) {
		this.snackName = snackName;
	}

	public int getSinglePrice() {
		return singlePrice;
	}

	public void setSinglePrice(int singlePrice) {
		this.singlePrice = singlePrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getShipping() {
		return shipping;
	}

	public void setShipping(String shipping) {
		this.shipping = shipping;
	}

	public String getWithraw() {
		return withraw;
	}

	public void setWithraw(String withraw) {
		this.withraw = withraw;
	}

	
}
