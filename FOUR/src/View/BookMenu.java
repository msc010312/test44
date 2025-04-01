package View;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Controller.FrontController;

public class BookMenu {
	
	private Scanner sc = new Scanner(System.in);
	private FrontController controller = FrontController.getInstance();
	private Map<String, Object> params = new HashMap();
	private Map<String, Object> response = new HashMap();
	
	public BookMenu() {
		
		params.put("endPoint", "/book");
		
		Boolean prev = true;
		while (prev) {
			System.out.println("--------------------------");
			System.out.println("BookMenu");
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
				System.out.print("Book_Code : ");
				params.put("Book_Code", sc.nextInt());
				System.out.print("Classification_Id : ");
				params.put("Classification_Id", sc.nextInt());
				System.out.print("Book_Name : ");
				params.put("Book_Name", sc.next());
				System.out.print("Book_Auther : ");
				params.put("Book_Auther", sc.next());
				System.out.print("Publisher : ");
				params.put("Publisher", sc.next());
				System.out.print("Isreserve : ");
				params.put("Isreserve", sc.nextInt());
				response = controller.execute(params);
				
				break;
			case 2:
				System.out.println("1 도서 조회");
				System.out.println("2 도서 전체 조회");
				System.out.print("조회 : ");
				if (sc.nextInt() == 1) {
					System.out.print("Book_Name : ");
					params.put("Book_Name", sc.next());
				}
				response = controller.execute(params);
				params.remove("Book_Name");
				break;
			case 3:
				System.out.print("Book_Code : ");
				params.put("Book_Code", sc.nextInt());
				System.out.print("Classification_Id : ");
				params.put("Classification_Id", sc.nextInt());
				System.out.print("Book_Auther : ");
				params.put("Book_Name", sc.next());
				System.out.print("Book_Name : ");
				params.put("Book_Auther", sc.next());
				System.out.print("Publisher : ");
				params.put("Publisher", sc.next());
				System.out.print("Isreserve : ");
				params.put("Isreserve", sc.nextInt());
				response = controller.execute(params);
				break;
			case 4:
				System.out.print("Book_Code : ");
				params.put("Book_Code", sc.nextInt());
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
