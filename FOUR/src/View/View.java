package View;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Controller.FrontController;


public class View {


	private String user_id;
	private String user_name;
	private String role;
	private Scanner sc = new Scanner(System.in);

	private FrontController controller;

	public View() throws Exception {
		controller = FrontController.getInstance();
	}

	

	public void MainMenu() {

		while (true) {
			System.out.println("--------------------------");
			System.out.println("MAIN");
			System.out.println("--------------------------");
			System.out.println("1 User");
			System.out.println("2 Rental");
			System.out.println("3 Book");
			System.out.println("4 Reserve");
			System.out.println("5 종료");
			System.out.print("번호 : ");
			int num = sc.nextInt();
			switch (num) {
			case 1:
				new UserMenu();
				break;
			case 2:
				new RentalMenu();
				break;
			case 3:
				new BookMenu();
				break;
			case 4:
				new ReserveMenu();
				break;
			case 5:
				System.out.println("프로그램을 종료합니다.");
				System.exit(-1);
			}
		}

	}
}
