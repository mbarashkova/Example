package task02DB;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Executor02 {

	public static boolean END = false;

	public static String FILENAME = "db01.txt";

	public static Scanner SCANNER = new Scanner(System.in);

	public static List<User> users = new ArrayList<User>();

	public Executor02() throws Exception {
		File file = new File(FILENAME);
		if (!file.exists()) {
			file.createNewFile();
		}
		try {
			readFromFile();
		} catch (Exception e) {
			System.out.println("Файл содержит некорректные данные. Завершение работы");
			return;
		}		
	}

	public static void main(String[] args) throws Exception {
		Executor02 executor = new Executor02();
		while (!END) {
			System.out.println("\nВведите действие >\n" + Command.allCommandsToString() + "\n");
			try {
				String answer = SCANNER.nextLine();
				parseAnswer(Integer.valueOf(answer));
			} catch (Exception e) {
				System.out.println("Некорректный ввод: " + e.getMessage());
			}
		}
		SCANNER.close();
	}

	public static void readFromFile() throws Exception {
		FileReader file = new FileReader(FILENAME);
		Scanner fileScanner = new Scanner(file);
		while (fileScanner.hasNextLine()) {
			String line = fileScanner.nextLine();
			String[] arg = line.split(",");

			int id = Integer.valueOf(arg[0]);
			String name = arg[1];
			String password = arg[2];
			int age = Integer.valueOf(arg[3]);
			long lastTime = Long.valueOf(arg[4]);

			String descryptedPassword = descrypt(password);
			
			User u = new User(id, name, descryptedPassword, age, lastTime);
			users.add(u);
		}
		fileScanner.close();
	}

	private static void parseAnswer(int id) throws Exception {
		Command command = Command.getCommand(id);

		switch (command) {
		case CLOSE:
			System.out.println("Завершение программы");
			END = true;
			break;
		case ADD:
			System.out.println("Добавление пользователя > ");
			addUser();
			break;
		case REMOVE:
			System.out.println("Удаление пользователя > ");
			removeUser();
			break;
		case EDIT:
			System.out.println("Удаление пользователя > ");
			editUser();
			break;
		case SHOW_USER_NAME:
			System.out.println("Пользователи: ");
			showUserNames();
			break;
		case SHOW_ALL:
			System.out.println("Все данные: ");
			showAll();
			break;
		case SHOW_SORTED_BY_TIME:
			System.out.println("Отсортированные данные: ");
			showSorted();
			break;
		default:

		}
	}

	private static void addUser() throws Exception {
		System.out.println("Введите имя: ");
		String name = SCANNER.nextLine();

		System.out.println("Введите пароль: ");
		String password = SCANNER.nextLine();

		System.out.println("Введите возраст: ");
		String ageString = SCANNER.nextLine();
		int age = Integer.valueOf(ageString);

		User user = new User(name, password, age);
		int id = getNewId();
		user.setId(id);

		users.add(user);
		updateUsers();

		System.out.println("Пользователь добавлен: " + user.toString());
	}

	private static int getNewId() {
		int maxId = 0;
		for ( int i = 0; i < users.size(); i++ ) {
			int userId = users.get(i).getId();
			if (userId > maxId) {
				maxId = userId;
			}
		}
		return maxId + 1;
	}
	
	private static void updateUsers() throws Exception {
		File f = new File(FILENAME);
		f.delete();
		f.createNewFile();

		FileWriter fr = null;
		BufferedWriter br = null;

		fr = new FileWriter(f, true);
		br = new BufferedWriter(fr);
		for (int i = 0; i < users.size(); i++) {
			User u = users.get(i);
			String encryptedPassword = encrypt(u.getPassword());
			String line = u.getId() + "," + u.getName() + "," + encryptedPassword + "," + u.getAge() + ","
					+ u.getLastTime();
			br.write(line);
			br.newLine();
		}

		br.close();
		fr.close();
	}

	private static void removeUser() throws Exception {
		System.out.println("Введите id пользователя для удаления: ");
		String idString = SCANNER.nextLine();
		int id = Integer.valueOf(idString);
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getId() == id) {
				users.remove(i);
				updateUsers();
				return;
			}
		}
		
		System.out.println("Такой пользователь не найден");
	}

	private static void editUser() throws Exception {
		System.out.println("Введите id пользователя для редактирования: ");
		String idString = SCANNER.nextLine();
		int id = Integer.valueOf(idString);
		
		User user = null;
		for (int i = 0; i < users.size(); i++) {
			User u = users.get(i);
			if (u.getId() == id) {
				user = u;
				break;
			}
		}
		
		if (user == null) {
			System.out.println("Такой пользователь не найден");
			return;
		}
		
		System.out.println("Имя пользователя: " + user.getName());
		System.out.println("Введите новое имя пользователя или нажмите Enter: ");
		String name = SCANNER.nextLine();

		System.out.println("Пароль пользователя: " + user.getPassword());
		System.out.println("Введите новый пароль пользователя или нажмите Enter: ");
		String password = SCANNER.nextLine();
		
		System.out.println("Возраст пользователя: " + user.getAge());
		System.out.println("Введите новый возраст пользователя или нажмите Enter: ");
		String age = SCANNER.nextLine();

		if ( !name.equals("") ) {
			user.setName(name);
		}
		
		if ( !password.equals("") ) {
			user.setPassword(password);
		}
		
		if ( !age.equals("") ) {
			int ageInt = Integer.valueOf(age);
			user.setAge(ageInt);
		}
		
		user.updateLastTime();
		
		updateUsers();
	}
	
	public void updateUserTime() {
		
	}
	
	private static void showUserNames() {
		users.sort(Comparator.comparing(User::getName));
		for (int i = 0; i < users.size(); i++) {
			User u = users.get(i);
			System.out.println(u.getName());
		}
	}

	private static void showAll() {
		users.sort(Comparator.comparing(User::getId));
		for (int i = 0; i < users.size(); i++) {
			System.out.println(users.get(i).toString());
		}
	}
	
	private static void showSorted() {
		users.sort(Comparator.comparing(User::getLastTime));
		for (int i = 0; i < users.size(); i++) {
			System.out.println(users.get(i).toString());
		}
	}
	
	private static String encrypt(String password) {
		int length = password.length();
		char[] encrypted = new char[length];
		for (int i = 0; i < password.length(); i++) {
			char s = password.charAt(i);
			encrypted[length-1-i] = (char) (s + i); 
		}
		return String.valueOf(encrypted);
	}
	
	private static String descrypt(String encrypted) {
		int length = encrypted.length();
		char[] password = new char[length];
		for (int i = 0; i < encrypted.length(); i++) {
			char s = encrypted.charAt(length - 1 - i);
			password[i] = (char) (s - i); 
		}
		return String.valueOf(password);
	}
	
	public User getUser(String name, String password)
	{
		for (User u : users) {
			if (u.getName().equals(name)) {
				if (u.getPassword().equals(password)) {
					return u;
				}
			}
		}
		
		return null;
	}
}
