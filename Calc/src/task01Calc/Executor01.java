package task01Calc;

import java.util.Scanner;

public class Executor01 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Calc c = new LineCalculator();
		boolean end = false;
		while (!end) {
			print("Press Enter to terminate the program");
			String line = scanner.nextLine();
			if (line.isEmpty()) {
				end = true;
				print("The End");
			} else {
				c.setExpression(line);
				c.compute();
				Double result = c.getResult();
				printDouble(result);
			}
		}

		scanner.close();
	}
	
	public static void printDouble(Double d) {
		if (d == null ) {
			System.out.println("null");
		} else {
			System.out.println("Result: " + d);
		}
	}

	public static void print(String str) {
		System.out.println(str);
	}
}
