package com.rest.hellowebservice.Udemy.user;
import java.util.ArrayList;
import java.util.Date;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
@Component
public class UserDao {
	private static ArrayList<User> users = new ArrayList<User>();
	private static int userCount = 3;
	static {
		users.add(new User(1, "pranav", new Date()));
		users.add(new User(2, "ravi", new Date()));
		users.add(new User(3, "ramu", new Date()));
	}
	public ArrayList<User> getusers() {
		return users;
	}
	public User saveUser(User user) {
		if (user.getId() == null) {
			user.setId(++userCount);
		}
		users.add(user);
		return user;
	}
	public User getUser(Integer id) {
		for (User user : users) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	public void deleteUser(Integer id) {
		
		users.removeIf(t->t.getId().equals(id));
		
	}
}
