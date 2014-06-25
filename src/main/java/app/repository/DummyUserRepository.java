package app.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import app.domain.User;

@Component
public class DummyUserRepository implements UserRepository {
	private final Map<Integer, User> users = new ConcurrentHashMap<Integer, User>();

	@Override
	public User findById(Integer id) {
		return this.users.get(id);
	}

	@Override
	public List<User> findAll() {
		List<User> users = new ArrayList<User>(this.users.values());
		Collections.sort(users, new Comparator<User>() {
			@Override
			public int compare(User o1, User o2) {
				return o1.getId() - o2.getId();
			}
		});
		return users;
	}

	@Override
	public User save(User user) {
		if (user.getId() == null) {
			user.setId(nextId());
		}
		this.users.put(user.getId(), user);
		return user;
	}

	@Override
	public void delete(Integer id) {
		this.users.remove(id);
	}

	private Integer nextId() {
		if (this.users.isEmpty()) {
			return 1;
		} else {
			return Collections.max(this.users.keySet()) + 1;
		}
	}
}
