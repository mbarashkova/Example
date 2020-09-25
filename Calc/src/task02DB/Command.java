package task02DB;

public enum Command {
	CLOSE(0, "Завершить"),
	ADD(1, "Добавить пользователя"),
	REMOVE(2, "Удалить пользователя"),
	EDIT(3, "Изменить данные пользователя"),
	SHOW_USER_NAME(4, "Показать имена пользователей"),
	SHOW_ALL(5, "Показать все данные отсортированные по id"),
	SHOW_SORTED_BY_TIME(6, "Показать отсортированные по времени");
	
	int id;
	String description;

	Command(int id, String description) {
		this.id = id;
		this.description = description;
	}
	
	public int getId() {
		return id;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static Command getCommand(int id) throws Exception {
		for (Command c : values()) {
			if (c.getId() == id) {
				return c;
			}
		}
		throw new Exception("Incorrect select " + id);
	}
	
	public static String allCommandsToString() {
		StringBuilder b = new StringBuilder();
		for (Command c : values()) {
			b.append(c.getId() + ": " + c.getDescription() + "\n");
		}
		return b.toString();
	}
}
