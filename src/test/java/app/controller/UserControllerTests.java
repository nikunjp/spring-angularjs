package app.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;

import app.domain.User;
import app.repository.DummyUserRepository;

public class UserControllerTests {

  private UserController controller;
  private DummyUserRepository repository;
  

  @Before
  public void before() {
    User user1 = new User();
    user1.setUsername("user1-username");
    user1.setEmail("user1-email");
    
    User user2 = new User();
    user2.setUsername("user2-username");
    user2.setEmail("user2-email");
    
    this.repository = new DummyUserRepository();
    this.repository.save(user1);
    this.repository.save(user2);

    this.controller = new UserController();
    this.controller.userRepository = this.repository;
  }

  @Test
  public void list() {
    List<User> users = this.controller.list();
    assertNotNull(users);
    assertEquals(2, users.size());
  }

  @Test
  public void find() {
    User user = this.controller.find(1);
    assertNotNull(user);
  }

  @Test(expected = UserController.UserNotFoundException.class)
  public void findUnknown() {
    this.controller.find(100);
  }

  @Test
  public void create() {
	User tmpUser = new User();
	tmpUser.setUsername("tmp-username");
	tmpUser.setEmail("tmp-email");
    HttpEntity<?> httpEntity = this.controller.create(tmpUser, new StringBuffer("/users"));
    assertNotNull(httpEntity);
    assertEquals(3, this.repository.findAll().size());
    assertEquals("/users/3", httpEntity.getHeaders().getLocation().toASCIIString());
  }

  @Test
  public void delete() {
    this.controller.delete(1);
    assertEquals(1, this.repository.findAll().size());
  }

  @Test
  public void update() {
	User tmpUser = new User();
	tmpUser.setUsername("updated-username");
	tmpUser.setEmail("tmp-email");
    this.controller.update(2, tmpUser);
    assertEquals("updated-username", this.repository.findById(2).getUsername());
  }
}
