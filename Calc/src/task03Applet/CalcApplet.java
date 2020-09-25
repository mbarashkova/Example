package task03Applet;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import task01Calc.Calc;
import task01Calc.LineCalculator;

public class CalcApplet extends JFrame {
	private final Font BIGGER_FONT = new Font("monspaced", Font.PLAIN, 20);
	private JTextField textfield;
	private boolean number = true;
	private boolean isComputed = false;

	public CalcApplet(String info) {
		JLabel infoLabel = new JLabel(info);
		textfield = new JTextField("", 12);
		textfield.setHorizontalAlignment(JTextField.RIGHT);
		textfield.setFont(BIGGER_FONT);

		ActionListener numberListener = new NumberListener();
		ActionListener computeListener = new ComputeListener();
		ActionListener clearListener = new ClearListener();

		String buttonOrder = "123456789.0=";
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(4, 4, 4, 4));
		for (int i = 0; i < buttonOrder.length(); i++) {
			String key = buttonOrder.substring(i, i + 1);
			if (key.equals(" ")) {
				buttonPanel.add(new JLabel(""));
			} else {
				JButton button = new JButton(key);
				if (key.equals("=")) {
					button.addActionListener(computeListener);
				} else {
					button.addActionListener(numberListener);
				}
				button.setFont(BIGGER_FONT);
				buttonPanel.add(button);
			}
		}

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4, 4, 4, 4));
		String[] opOrder = { "+", "-", "*", "/", "", "", "", "C" };
		for (int i = 0; i < opOrder.length; i++) {
			JButton button = new JButton(opOrder[i]);
			if (opOrder[i].equals("C")) {
				button.addActionListener(clearListener);
			} else {
				button.addActionListener(numberListener);
			}
			button.setFont(BIGGER_FONT);
			panel.add(button);
		}
		JPanel pan = new JPanel();
		pan.setLayout(new BorderLayout(4, 4));
		pan.add(textfield, BorderLayout.NORTH);
		pan.add(buttonPanel, BorderLayout.CENTER);
		pan.add(panel, BorderLayout.EAST);
		pan.add(infoLabel, BorderLayout.SOUTH);
		this.setContentPane(pan);
		this.pack();
		this.setTitle("Calculator");
		this.setResizable(false);
	}

	class NumberListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (isComputed) {
				textfield.setText("");
				isComputed = false;
			}

			String digit = event.getActionCommand();
			if (number) {
				textfield.setText(digit);
				number = false;
			} else {
				textfield.setText(textfield.getText() + digit);
			}
		}
	}

	class ComputeListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (isComputed) {
				return;
			}
			String expression = textfield.getText();

			Calc c = new LineCalculator();
			c.setExpression(expression);
			String result;
			try {
				c.compute();
				result = String.valueOf(c.getResult());
			} catch (Throwable e) {
				result = "Ошибка";
			}
			textfield.setText(expression + "=" + result);
			isComputed = true;
		}
	}

	class ClearListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			textfield.setText("");
			isComputed = false;
		}
	}
}