package edu.kh.jdbc.user.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import edu.kh.jdbc.main.view.MainView;
import edu.kh.jdbc.user.model.service.UserService;
import edu.kh.jdbc.user.vo.User;

public class UserView {
	
	private Scanner sc = new Scanner(System.in);
	
	private UserService service = new UserService();
	
	private User loginUser = null;
	
	private int input = -1;

	/** 회원 기능 메뉴 화면
	 * @param loginUser (로그인된 회원 정보)
	 */
	public void userMenu(User loginUser) {
 
		this.loginUser = loginUser;
		
		do {
			try {
				System.out.println("\n***** 회원 기능 ***** \n");
				System.out.println("1. 내 회원 정보 조회");
				System.out.println("2. 내 회원 정보 수정(전화번호, 주소)");
				System.out.println("3. 비밀번호 변경");
				System.out.println("4. 회원 탈퇴");
				System.out.println("0. 메인 메뉴로 이동");
				
				System.out.print("\n메뉴 선택 : ");
				
				input = sc.nextInt();
				sc.nextLine();
				System.out.println();
				
				switch(input) {
				case 1: selectUserInfo(); break;
				case 2: updateUser(); break;
				case 3: updatePw(); break;
				case 4: secession(); break;
				case 0: System.out.println("[메인 메뉴로 이동합니다]"); break;
				default: System.out.println("메뉴에 작성된 번호만 입력해주세요.");
				}
				System.out.println();
				
			} catch(InputMismatchException e) {
				System.out.println("\n<<입력 형식이 올바르지 않습니다.>>");
				sc.nextLine();
			}
			
			
		} while(input !=0);
	}
	
	/**
	 * 내 회원 정보 조회 (회원번호)/아이디/비밀번호/이름/전화번호/주소/가입날짜
	 */
	private void selectUserInfo() {
		System.out.println("\n[내 회원 정보]\n");
		
		System.out.println("회원 번호 : "+loginUser.getUserNo());
		System.out.println("아이디 : "+loginUser.getUserId());
		System.out.println("비밀번호 : "+loginUser.getUserPw());
		System.out.println("이름 : "+loginUser.getUserName());
		System.out.println("전화 번호 : "+loginUser.getUserPhone());
		System.out.println("주소 : "+loginUser.getUserAddress());
		System.out.println("가입 날짜 : "+loginUser.getEnrollDate());
	}

	/**
	 * 내 회원 정보 수정 (전화번호, 주소)
	 */
	private void updateUser() {
		
		try {
			System.out.println("\n[내 회원 정보 수정]\n");
			System.out.print("변경할 전화번호 : ");
			String userPhone = sc.next();
			sc.nextLine();
			System.out.print("변경할 주소 : ");
			String userAddress = sc.nextLine();
			
			User user = new User();
			user.setUserNo(loginUser.getUserNo());
			user.setUserPhone(userPhone);
			user.setUserAddress(userAddress);
			
			int result = service.updateUser(user);
			
			if(result>0) {
				loginUser.setUserPhone(userPhone);
				loginUser.setUserAddress(userAddress);
				System.out.println("\n[회원 정보가 수정되었습니다.]\n");
				selectUserInfo();
				
			} else {
				System.out.println("\n[회원 정보를 수정하지 못했습니다.]\n");
			}

		} catch(Exception e) {
			System.out.println("\n<<회원 정보 수정 중 예외 발생>>\n");
			e.printStackTrace();
		}
	}
	
	/**
	 * 내 비밀번호 변경
	 */
	private void updatePw() {
		System.out.println("\n[비밀번호 변경]\n");
		
		try {
			System.out.print("현재 비밀번호 : ");
			String currentPw = sc.next();
			
			String newPw1 = null;
			String newPw2 = null;
			
			while(true) {
				System.out.print("새 비밀번호 : ");
				newPw1 = sc.next();
				
				System.out.print("새 비밀번호 확인 : ");
				newPw2 = sc.next();
				
				if(newPw1.equals(newPw2)) {
					break;
				} else {
					System.out.println("\n새 비밀번호가 일치하지 않습니다. 다시 입력해주세요.\n");
				}
			} //while end
			
			int result = service.updatePw(currentPw, newPw1, loginUser.getUserNo());
			
			if(result>0) {
				System.out.println("\n[비밀번호가 변경되었습니다.]\n");
				
			}else {
				System.out.println("\n[현재 비밀번호가 일치하지 않습니다.]\n");
			}
			
		}catch(Exception e) {
			System.out.println("\n<<비밀번호 변경 중 예외 발생>>\n");
			e.printStackTrace();
		}
	}
	
	/**
	 * 회원 탈퇴
	 */
	private void secession() {
		System.out.println("\n[회원 탈퇴]\n");
		
		try {
			System.out.print("비밀번호를 입력하세요 : ");
			String userPw = sc.next();
			
			while(true) {
				System.out.print("정말 탈퇴하시겠습니다?(Y/N) : ");
				char se = sc.next().toUpperCase().charAt(0);
				
				if(se=='Y') {
					
					int result = service.secession(userPw, loginUser.getUserNo());
					
					if(result>0) {
						System.out.println("\n[탈퇴 완료]\n");
						
						input = 0;
						MainView.loginUser = null;
					} else {
						System.out.println("\n[비밀번호가 일치하지 않습니다.]\n");
					}
					
					break; // while문 종료
					
				} else if(se=='N') {
					System.out.println("\n[회원 탈퇴가 취소 되었습니다.]\n");
					break;
					
				} else {
					System.out.println("\n[Y 또는 N만 입력해주세요.]\n");
				}
			}
		} catch(Exception e) {
			System.out.println("\n<<회원 탈퇴 중 예외 발생>>\n");
			e.printStackTrace();
		}
	}
	
}
