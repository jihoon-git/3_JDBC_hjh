package edu.kh.jdbc.user.vo;


public class User {
	
	private int userNo; // 회원 번호
	private String userId; // 회원 아이디
	private String userPw; // 회원 비밀번호
	private String userName; // 이름
	private String userPhone; // 전화번호
	private String userAddress; // 주소
	private String enrollDate; // 가입일
	private String secessionFlag; // 탈퇴 여부

	
	public User() {	}

	
	
	public User(String userId, String userPw, String userName, String userPhone, String userAddress) {
		super();
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.userPhone = userPhone;
		this.userAddress = userAddress;
	}



	public User(int userNo, String userId, String userPw, String userName, String userPhone, String userAddress,
			String enrollDate) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.userPhone = userPhone;
		this.userAddress = userAddress;
		this.enrollDate = enrollDate;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}

	public String getSecessionFlag() {
		return secessionFlag;
	}

	public void setSecessionFlag(String secessionFlag) {
		this.secessionFlag = secessionFlag;
	}
	
	
	
}
