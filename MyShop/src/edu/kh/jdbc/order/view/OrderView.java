package edu.kh.jdbc.order.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc.order.model.service.OrderService;
import edu.kh.jdbc.order.vo.Order;
import edu.kh.jdbc.user.vo.User;

public class OrderView {
	
	private Scanner sc = new Scanner(System.in);
	
	private OrderService service = new OrderService();
	
	private User loginUser = null;
	
	private int input = -1;
	
	public void orderMenu(User loginUser) {
		this.loginUser=loginUser;
		
		do {
			try {
				System.out.println("\n***** 주문 기능 *****\n");
				System.out.println("1. 전체 상품 목록 조회");
				System.out.println("2. 주문하기");
				System.out.println("3. 내 주문 내역 조회");
				System.out.println("4. 주문수량 변경");
				System.out.println("5. 주문 취소");
				System.out.println("6. 취소 내역 조회");
				System.out.println("0. 메인 메뉴로 이동");
				System.out.print("\n메뉴 선택 : ");
				
				input = sc.nextInt();
				sc.nextLine();
				
				System.out.println();
				
				switch(input) {
				case 1: selectProduct(); break;
				case 2: insertOrder(); break;
				case 3: selectMyOrder(); break;
				case 4: updateQuantity(); break;
				case 5: withdrawOrder(); break;
				case 6: selectWithdraw(); break;
				case 0:  System.out.println("[메인 메뉴로 이동합니다]"); break;
				default: System.out.println("메뉴에 작성된 번호만 입력해주세요.");
				}
				System.out.println();
				
			}catch(InputMismatchException e) {
				System.out.println("\n<<입력 형식이 올바르지 않습니다.>>");
				sc.nextLine();
			}
		} while(input !=0);
	}
	
	
	/**
	 * 1. 전체 상품 목록 조회
	 */
	private void selectProduct() {
		System.out.println("\n[전체 상품 목록 조회]\n");
		
		try {
			List<Order> orderList = service.selectProduct();
			
			if(orderList.isEmpty()) {
				System.out.println("\n[조회 결과가 없습니다.]");
			}else {
				
				System.out.println("     상품번호    상품명   가격");
				System.out.println("-------------------------");
				for(Order order : orderList) {
					
					System.out.printf("%d  |  %s  |  %d"+"원\n", 
							order.getSnackNo(),
							order.getSnackName(),
							order.getSinglePrice()
							);
					}
			}
		}catch(Exception e) {
			System.out.println("\n<<전체 상품 목록 조회 중 예외 발생>>\n");
		e.printStackTrace();
		}
	}
	
	/**
	 * 2. 주문 하기
	 */
	private void insertOrder() {
		System.out.println("[주문 하기]");
		
		try {
			System.out.print("상품 번호 : ");
			int snackNo = sc.nextInt();
			
			System.out.print("주문 수량 : ");
			int quantity = sc.nextInt();
			
			Order order = new Order();
			order.setUserNo(loginUser.getUserNo());
			order.setSnackNo(snackNo);
			order.setQuantity(quantity);
			
			int result = service.insertOrder(order);
			
			if(result>0) {
				
				System.out.println("\n[주문이 완료 되었습니다.]\n");
				
			}else {
				System.out.println("\n[주문 실패]\n");
			}
		} catch(Exception e) {
			System.out.println("\n<<주문하기 실행 중 예외 발생>>\n");
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 *  3. 내 주문 내역 조회 (전체 출력) - 주문번호/주문날짜/상품명/수량/가격/배송상태
	 */
	private void selectMyOrder() {
		
		System.out.println("\n[내 주문 내역 조회]\n");
		
		try {
			List<Order> orderList = service.selectMyOrder();
			
			if(orderList.isEmpty()) {
				System.out.println("\n[조회 결과가 없습니다.]");
			} else {
				System.out.println(" 주문번호      주문날짜       상품명     수량    가격     배송상태");
				System.out.println("----------------------------------------------------------");
				
				for(Order order : orderList ) {
					System.out.printf("%d  %s  %s   %d개  %d원   %s",
							order.getOrderNo(),
							order.getOrderDate(),
							order.getSnackName(),
							order.getQuantity(),
							order.getPrice(),
							order.getShipping());
				}
			}

		} catch(Exception e) {
			System.out.println("\n<<내 주문 내역 조회 중 예외 발생>>\n");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 4. 주문수량 변경 - 주문 번호 입력 받아 수량 변경
	 */
	private void updateQuantity() {
		
		try {
			System.out.println("\n[주문 수량 변경]\n");
			System.out.print("주문 번호 입력 : ");
			int orderNo = sc.nextInt();
			sc.nextLine();
			
			boolean flag = true;
			
			System.out.print("변경할 수량 입력 : ");
			int quantity = sc.nextInt();
			
			Order order = new Order();
			order.setUserNo(loginUser.getUserNo());
			order.setOrderNo(orderNo);
			order.setQuantity(quantity);

			int result = service.updateQuantity(order);

			if(result>0) {
				
				System.out.println("\n[주문 수량이 변경 되었습니다.]\n");
			} else {
				System.out.println("\n[존재하지 않는 주문 번호입니다.]\n");
			}
		
		
		} catch(Exception e) {
			System.out.println("\n<<주문 수량 변경 중 예외 발생>>\n");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 5. 주문 취소 - 주문 번호 입력 받아 주문 취소
	 */
	private void withdrawOrder() {
		System.out.println("\n[주문 취소]\n");
		
		try {
			System.out.print("주문 번호 입력 : ");
			int orderNo = sc.nextInt();
			
			while(true) {
				System.out.print("정말 주문을 취소 하시겠습니다?(Y/N) : ");
				char ce = sc.next().toUpperCase().charAt(0);
				
				if(ce == 'Y') {
					int result = service.withdrawOrder(orderNo, loginUser.getUserNo());
					
					if(result>0) {
						System.out.println("\n[선택한 주문이 취소되었습니다.]\n");
					} else {
						System.out.println("\n[존재하지 않는 주문 번호입니다.]\n");
					}
					break;
				} else if(ce=='N') {
					System.out.println("\n[주문 취소를 철회했습니다.]\n");
					break;
				}else {
					System.out.println("\n[Y 또는 N만 입력해주세요.]\n");
				}
			}

		
		} catch(Exception e) {
			System.out.println("\n<<주문 취소 중 예외 발생>>\n");
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 6. 취소 내역 조회 - 주문번호/주문날짜/상품명/수량/가격/배송상태
	 */
	private void selectWithdraw() {
		
		System.out.println("\n[취소 내역 조회]\n");
		
	}

}
