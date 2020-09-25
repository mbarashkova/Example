package task02DB;

import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
	
	private int id;
	private String name;
	private String password;
	private int age;
	private long lastTime;
	
	public User(String name, String password, int age) {
		this.name = name;
		this.password = password;
		this.age = age;
		this.lastTime = System.currentTimeMillis();
	}
	
	public User(int id, String name, String password, int age, long lastTime) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.age = age;
		this.lastTime = lastTime;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAge() {
		return age;
	}
	
	public long getLastTime() {
		return lastTime;
	}
	
	public void updateLastTime() {
		this.lastTime = System.currentTimeMillis();
	}
	
	public String getFormatTime() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(lastTime));
	}
	
	public String toString() {
		return "[" + getId() + ", " + getName() + ", " + getPassword() + ", " + getAge() + ", " + getFormatTime() + "]";
	}
	
}