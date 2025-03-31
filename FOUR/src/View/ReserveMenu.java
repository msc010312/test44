package View;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Controller.FrontController;

public class ReserveMenu {

	private Scanner sc = new Scanner(System.in);
	private FrontController controller = FrontController.getInstance();
	private Map<String, Object> params = new HashMap();
	private Map<String, Object> response = new HashMap();

	public ReserveMenu() {

		params.put("endPoint", "/reserve");

		Boolean prev = true;
		while (prev) {
			System.out.println("--------------------------");
			System.out.println("ReserveMenu");
			System.out.println("--------------------------");
			System.out.println("1 Insert(C)");
			System.out.println("2 Select(R)");
			System.out.println("3 Update(U)");
			System.out.println("4 Delete(D)");
			System.out.println("5 종료");
			System.out.print("번호 : ");
			int num = sc.nextInt();
			params.put("serviceNo", num);
			switch (num) {
			// 예약 신청
			case 1: {
				System.out.println("Rental_Id : ");
				params.put("rental_id", sc.nextInt());
				System.out.println("User_Id : ");
				params.put("user_id", sc.nextInt());
				System.out.println("order ? : ");
				params.put("reserve_order", sc.next());
				response = controller.execute(params);
				break;
			}

			// 예약조회
			case 2: {
				System.out.println("원하는 서비스를 선택하세요.");
				System.out.println("1 : 유저 아이디별 예약 조회");
				System.out.println("2 : 렌탈 아이디별 예약 조회");
				System.out.println("3 : 전체 예약 조회");
				int searchNo = sc.nextInt();
				switch (searchNo) {
				// id별 조회
				case 1: {
					System.out.print("User_Id : ");
					params.put("user_id", sc.nextInt());
					break;
				}

				// 렌탈 id별 조회
				case 2: {
					System.out.print("Rental_Id : ");
					params.put("rental_id", sc.nextInt());
					break;
				}
				// 전체 예약 조회
				case 3: {
					break;
				}
				}
				response = controller.execute(params);
				break;
			}

			// 예약 수정
			case 3: {

				System.out.print("Rental_Id : ");
				params.put("rental_id", sc.nextInt());
				System.out.print("User_Id : ");
				params.put("user_id", sc.nextInt());
				System.out.print("Order : ");
				params.put("reserve_order", sc.next());
				response = controller.execute(params);
				break;
			}

			// 예약 삭제
			case 4: {
				System.out.print("User_Id : ");
				params.put("user_id", sc.nextInt());
				System.out.print("Rental_Id : ");
				params.put("rental_id", sc.nextInt());
				response = controller.execute(params);
				break;
			}
			// 종료
			case 5:
				prev = !prev;
				break;
			}
			for (String key : response.keySet()) {
				System.out.println(key + " : " + response.get(key));
			}
		}
	}
}
