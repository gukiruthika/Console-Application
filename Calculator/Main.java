package Calculator;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Main main = new Main();
		main.operate();

	}

	void operate() {
		try (Scanner sn = new Scanner(System.in)) {
			Calculation obj = new Calculation();
			System.out.println("Calculator opened");
			do {
				System.out.println("\n------------------------------------------------------------------------");
				System.out.println("| + | - | * | / | ^ | % | ! | pi | sqrt | cbrt | log | sin | cos | tan |");
				System.out.println("------------------------------------------------------------------------");
				System.out.println("Enter the problem..");
				try {

					String problem = sn.nextLine();
					obj.calculate(problem);
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("\nEnter 1 to Continue..");
			} while (sn.nextLine().equals("1"));
			System.out.print("\nCalculator closed");

		}
	}

}
