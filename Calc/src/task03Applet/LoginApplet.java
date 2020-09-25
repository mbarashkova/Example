package task03Applet;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import task02DB.Executor02;
import task02DB.User;

public class LoginApplet extends JFrame {

	private JTextField loginField;
	private JTextField passwordField;
	private Executor02 db;
	
    public LoginApplet() throws Exception {
    	db = new Executor02();
        JPanel panel = new JPanel();
        
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JLabel loginLabel = new JLabel("Введите логин: ");
        panel.add(loginLabel);
        loginField = new JTextField("", 15);
        panel.add(loginField);
        
        JLabel passwordLabel = new JLabel("Введите пароль: ");
        panel.add(passwordLabel);        
        passwordField = new JTextField("", 15);
        panel.add(passwordField);
    	
    	JPanel buttonPanel = new JPanel();
    	buttonPanel.setLayout(new FlowLayout());
    	
        JButton buttonLogin = new JButton("Войти");
        buttonLogin.addActionListener(new LoginAction());
        buttonPanel.add(buttonLogin);
    	
        JButton buttonExit = new JButton("Выход");
        buttonExit.addActionListener(new ExitAction());
        buttonPanel.add(buttonExit);
        
        panel.add(buttonPanel);
    	
        this.setContentPane(panel);
        this.pack();
        this.setTitle("Login");
        this.setResizable(false);
    }
    
    class LoginAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String login = loginField.getText();
			String password = passwordField.getText();

			String error = null;
			User user = null;
			
			if (login.isEmpty()) {
				error = "Логин не может быть пустым";
			} else if (password.isEmpty()) {
				error = "Пароль не может быть пустым";
			} else {
				user = db.getUser(login, password);
				if (user == null) {
					error = "Пользователь не найден";
				}
			}
			
			if (error != null) {
				JOptionPane.showMessageDialog(LoginApplet.this, error);
			} else {
				System.out.println(user);
				try {
					String line = user.getName() + ", возраст " + user.getAge();
					JFrame frame = new CalcApplet(line);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setVisible(true);
				} catch (Exception eee) {
					System.out.println("Какая-то ошибка");
				}
			}
        }
    }
    
	class ExitAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
}

