package View;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Controller.FrontController;

public class UserMenu {

	private Scanner sc = new Scanner(System.in);
	private FrontController controller = FrontController.getInstance();
	private Map<String, Object> params = new HashMap();
	private Map<String, Object> response = new HashMap();

	public UserMenu() {

		params.put("endPoint", "/user");

		Boolean prev = true;
		while (prev) {
			System.out.println("--------------------------");
			System.out.println("UserMenu");
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
			case 1:
				System.out.print("User_Id : ");
				params.put("user_id", sc.nextInt());
				System.out.print("User_Name : ");
				params.put("user_name", sc.next());
				System.out.print("User_Identity : ");
				params.put("user_identity", sc.next());
				System.out.print("User_Phone : ");
				params.put("user_phone", sc.next());
				System.out.print("User_Addr : ");
				params.put("user_addr", sc.next());
				response = controller.execute(params);
				break;
			case 2:
				System.out.println("1 유저조회");
				System.out.println("2 전체조회");
				System.out.print("조회 : ");
				if (sc.nextInt() == 1) {
					System.out.print("User_Id : ");
					params.put("user_id", sc.nextInt());
				}
				response = controller.execute(params);
				params.remove("user_id");
				break;
			case 3:
				System.out.print("User_Id : ");
				params.put("user_id", sc.nextInt());
				System.out.print("User_Name : ");
				params.put("user_name", sc.next());
				System.out.print("User_Identity : ");
				params.put("user_identity", sc.next());
				System.out.print("User_Phone : ");
				params.put("user_phone", sc.next());
				System.out.print("User_Addr : ");
				params.put("user_addr", sc.next());
				response = controller.execute(params);
				break;
			case 4:
				System.out.print("User_Id : ");
				params.put("user_id", sc.nextInt());
				response = controller.execute(params);
				break;
			case 5:
				prev = !prev;
			}
			for (String key : response.keySet()) {
				System.out.println(key + " : " + response.get(key));
			}
		}
	}
}
