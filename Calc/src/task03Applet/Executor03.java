package task03Applet;

import javax.swing.JFrame;

public class Executor03 {
	public static void main(String[] args) {
		try {
			JFrame frame = new LoginApplet();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		} catch (Exception e) {
			System.out.println("Какая-то ошибка");
		}
	}
}