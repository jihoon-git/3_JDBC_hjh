package edu.kh.jdbc.main.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import edu.kh.jdbc.main.model.service.MainService;
import edu.kh.jdbc.user.view.UserView;
import edu.kh.jdbc.user.vo.User;

/*
-- 메인메뉴
1. 로그인
2. 회원가입
0. 종료

-- 로그인 메뉴
1. 로그인
1) 회원 기능
2) 주문 기능
0) 로그아웃
99) 프로그램 종료

-- 회원가입 메뉴
2. 회원가입
- 아이디
- 비밀번호
- 이름
- 전화번호
- 주소

-- (로그인 후)회원 기능 메뉴
1. 내 회원정보 조회 - (회원번호)/아이디/비밀번호/이름/전화번호/주소/가입날짜/(탈퇴여부(N))
2. 회원정보 수정 (전화번호, 주소)
3. 비밀번호 수정
4. 회원 탈퇴
0. 메인메뉴로 이동

-- (로그인 후) 주문 기능 메뉴
(1. 전체 상품 목록 조회)
(1. 주문하기)
(-- 주문메뉴 따로 뺄지 생각해볼 것)
1. 내 주문 내역 조회 (전체 출력) - (회원번호)/주문번호/주문날짜/상품명/옵션/수량/가격/배송상태/(취소여부(N))
2. 주문수량 변경 - 주문 날짜와 상품명 입력받아 수량 변경
(배송 상태가 출고전이어야 함)
3. 주문 취소 - 주문 번호 입력 받아 주문 취소
(배송 상태가 출고전일때만 가능)
4. 취소 내역 조회 - 취소여부 Y인 주문만 출력
0. 메인메뉴로 이동

--(로그인 후) 관리자 메뉴(보류)
1. 전체 회원 정보 조회
2. 전체 주문 내역 조회
3. 취소 처리

 * 
 * */

public class MainView {
	
	private Scanner sc = new Scanner(System.in);
	
	private MainService service = new MainService();
	
	// 로그인 회원 정보
	public static User loginUser = null;
	
	private UserView userView = new UserView();

	/**
	 *  메인 메뉴 출력 메서드
	 */
	public void mainMenu() {
		int input = -1;
		
		do {
			try {
				if (loginUser == null) {	
					System.out.println("\n***** 마이페이지 (MyShop) *****\n");
					System.out.println("1. 로그인");
					System.out.println("2. 회원가입");
					System.out.println("0. 프로그램 종료");
					System.out.print("\n메뉴 선택 : ");
					input = sc.nextInt();
					sc.nextLine();
					System.out.println();
							
					switch(input) {
					case 1: login(); break;
					case 2: signUp(); break;
					case 0: System.out.println("프로그램을 종료합니다."); break;
					default: System.out.println("메뉴에 작성된 번호만 입력해주세요.");
					}
				} else {
						System.out.println("***** 로그인 메뉴 *****");
						System.out.println("1. 회원 기능");
						System.out.println("2. 주문 기능");
						System.out.println("0. 로그아웃");
						System.out.println("99. 프로그램 종료");
						System.out.print("\n메뉴 선택 : ");	
						input = sc.nextInt();
						sc.nextLine();
						System.out.println();
						
						switch(input) {
						
						case 1: userView.userMenu(loginUser); break;
						case 2:  break;
						case 0: loginUser = null;
							System.out.println("\n[로그아웃 되었습니다.]\n");
							input=-1; break;
						case 99: System.out.println("프로그램이 종료 되었습니다."); 
							input=0; break;
						default: System.out.println("메뉴에 있는 번호만 입력해주세요.");

						}
						System.out.println();
				}
				
			} catch(InputMismatchException e) {
				System.out.println("\n<<입력 형식이 올바르지 않습니다.>>");
				sc.nextLine();
			}
				
			
		} while(input !=0);
		
	}
	
	/**
	 * 회원 가입 화면
	 */
	private void signUp() {
		System.out.println("[회원 가입]");
		String userId = null;
		String userPw1 = null;
		String userPw2 = null;
		String userName = null;
		String phone = null;
		String address = null;
		
		try {
			while(true) {
				System.out.print("아이디 : ");
				userId = sc.next();
				
				int result = service.idCheck(userId);
				
				if(result ==0) {
					System.out.println("[사용 가능한 아이디 입니다.]");
					break;
				} else {
					System.out.println("[이미 사용중인 아이디 입니다.]");
				}
			}
				
			while(true) {
					System.out.print("비밀번호 : ");
					userPw1 = sc.next();
					System.out.print("비밀번호 확인 : ");
					userPw2 = sc.next();
					
					System.out.println();
					
					if(userPw1.equals(userPw2)) {
						System.out.println("비밀번호가 일치합니다.");
						break;
					} else {
						System.out.println("비밀번호가 일치하지 않습니다.");
					}
					System.out.println();
				}
				
				System.out.print("이름 입력 : ");
				userName = sc.next();
				System.out.print("핸드폰 번호 입력 : ");
				phone = sc.next();
				sc.nextLine();
				System.out.print("주소 입력 : ");
				address = sc.nextLine();
				
				User user = new User(userId, userPw1, userName, phone, address);
				
				int result = service.signUp(user);
				
				
				System.out.println();
				
				if(result>0) {
					System.out.println("회원 가입이 완료되었습니다.");
				} else {
					System.out.println("[회원 가입 실패]");
				}
				System.out.println();
			
		} catch (Exception e) {
			System.out.println("\n<<회원 가입 중 예외 발생>>");
			e.printStackTrace();
		}
	}
	
	/**
	 * 로그인 화면
	 */
	private void login() {
		System.out.println("[로그인]");
		System.out.print("아이디 : ");
		String userId = sc.next();
		System.out.print("비밀번호 : ");
		String userPw = sc.next();
		
		try {
			loginUser = service.login(userId, userPw);
			
			if(loginUser != null) {
				
				System.out.println(loginUser.getUserName() + "님 환영합니다.");
				
			} else {
				System.out.println("[아이디 또는 비밀번호가 일치하지 않습니다.]");
			}
			System.out.println();
			
		}catch(Exception e) {
			System.out.println("\n<<로그인 중 예외 발생>>");
			e.printStackTrace();
		}
		
	}
	
}
