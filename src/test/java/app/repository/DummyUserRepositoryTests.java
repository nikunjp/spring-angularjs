package app.repository;

import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import app.domain.User;

public class DummyUserRepositoryTests {
  private DummyUserRepository repository;
  
  @Before
  public void before() {
    this.repository = new DummyUserRepository();
  }

  @Test
  public void saveAndGet() {
    User user = new User();
    user.setUsername("test-username");
    user.setEmail("test-email");

    user = this.repository.save(user);
    assertNotNull(user.getId());

    User user2 = this.repository.findById(user.getId());
    assertEquals(user, user2);
  }

  @Test
  public void saveAndDelete() {
    User user = new User();
    user.setUsername("test-username");
    user.setEmail("test-email");

    user = this.repository.save(user);
    assertNotNull(user.getId());

    this.repository.delete(user.getId());
    assertNull(this.repository.findById(user.getId()));
  }

  @Test
  public void saveAndUpdate() {
    User user = new User();
    user.setUsername("test-username");
    user.setEmail("test-email");

    user = this.repository.save(user);
    Integer id = user.getId();

    user = this.repository.save(user);
    assertEquals(id, user.getId());

  }

  @Test
  public void listAll() {
    User user = new User();
    user.setUsername("test-username");
    user.setEmail("test-email");

    user = this.repository.save(user);
    user.setId(null);
    this.repository.save(user);

    List<User> users = this.repository.findAll();
    assertNotNull(users);
    assertEquals(2, users.size());
  }
}
