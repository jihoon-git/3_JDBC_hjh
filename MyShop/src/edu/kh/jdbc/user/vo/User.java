package edu.kh.jdbc.user.vo;

public class User {
//	USER_NO NUMBER
//	USER_ID VARCHAR2(20)
//	USER_PW VARCHAR2(30)
//	USER_NAME VARCHAR2(30)
//	USER_PHONE VARCHAR2(30)
//	USER_ADDR VARCHAR2(70)
//	ENROLL_DATE DATE
//	SECESSION_FL CHAR(1)
	
	private int userNo;
	private String userId;
	private String userPw;
	private String userName;
	private String userPhone;
	private String userAddress;
	private String enrollDate;
	private String secessionFlag;
	
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
