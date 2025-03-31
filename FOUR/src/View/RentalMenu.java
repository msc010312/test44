package View;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Controller.FrontController;

public class RentalMenu {

	private Scanner sc = new Scanner(System.in);

	private FrontController controller = FrontController.getInstance();
	Map<String, Object> res = new HashMap();

	public RentalMenu() {
		Boolean prev = true;
		while (prev) {
			System.out.println("--------------------------");
			System.out.println("RentalMenu");
			System.out.println("--------------------------");
			System.out.println("1 Insert(C)");
			System.out.println("2 Select(R)");
			System.out.println("3 Update(U)");
			System.out.println("4 Delete(D)");
			System.out.println("5 종료");
			System.out.print("번호 : ");
			int num = sc.nextInt();
			switch (num) {
			case 1:
				insertRT();
				break;
			case 2:
				selectRT();
				break;
			case 3:
				updateRT();
				break;
			case 4:
				deleteRT();
				break;
			case 5:
				prev = !prev;
			}
		}
	}

	public void insertRT() {
		System.out.println("--------------------------");
		System.out.println("대여정보 입력");
		System.out.println("--------------------------");
		System.out.print("Rental ID : ");
		int id = sc.nextInt();
		System.out.println("Book Code : ");
		int bk = sc.nextInt();
		System.out.print("User ID : ");
		int ui = sc.nextInt();

		// 요청처리
		Map<String, Object> params = new HashMap();
		params.put("endPoint", "/rental");
		params.put("serviceNo", 1);
		params.put("rental_id", id);
		params.put("book_code", bk);
		params.put("user_id", ui);

		res = controller.execute(params);
		for (String key : res.keySet()) {
			System.out.println(key + " : " + res.get(key));
		}
	}

	public void selectRT() {
		System.out.println("--------------------------");
		System.out.println("대여 정보 조회");
		System.out.println("--------------------------");
		System.out.print("User ID: ");
		int ui = sc.nextInt();

		Map<String, Object> params = new HashMap<>();
		params.put("endPoint", "/rental");
		params.put("serviceNo", 2);
		params.put("user_id", ui);

		res = controller.execute(params);
		for (String key : res.keySet()) {
			System.out.println(key + " : " + res.get(key));
		}
	}

	public void updateRT() {

	}

	public void deleteRT() {
		System.out.println("--------------------------");
        System.out.println("대여 정보 삭제");
        System.out.println("--------------------------");
        System.out.print("Rental ID: ");
        int id = sc.nextInt();

        Map<String, Object> params = new HashMap<>();
        params.put("endPoint", "/rental");
        params.put("serviceNo", 4);
        params.put("rental_id", id);

        res = controller.execute(params);
        for (String key : res.keySet()) {
			System.out.println(key + " : " + res.get(key));
		}
	}
}
