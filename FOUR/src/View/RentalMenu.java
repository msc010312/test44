package View;

import java.util.Scanner;

public class RentalMenu {
	
	private Scanner sc = new Scanner(System.in);
	
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
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				prev = !prev;
			}
		}
	}
}
