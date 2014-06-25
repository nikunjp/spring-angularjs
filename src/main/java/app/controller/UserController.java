package app.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriTemplate;

import app.domain.User;
import app.repository.UserRepository;

@Controller
@RequestMapping("/users")
public class UserController {

  @Autowired
  UserRepository userRepository;

  @RequestMapping(method = RequestMethod.GET)
  public @ResponseBody List<User> list() {
    return this.userRepository.findAll();
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public @ResponseBody User find(@PathVariable("id") Integer id) {
    User user = this.userRepository.findById(id);
    if (user == null) {
      throw new UserNotFoundException(id);
    }
    return user;
  }

  @RequestMapping(method = RequestMethod.POST, consumes = {"application/json"})
  @ResponseStatus(HttpStatus.CREATED)
  public HttpEntity<?> create(@RequestBody User user, @Value("#{request.requestURL}") StringBuffer parentUri) {
    user = this.userRepository.save(user);
    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(childLocation(parentUri, user.getId()));
    return new HttpEntity<Object>(headers);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable("id") Integer id) {
    this.userRepository.delete(id);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void update(@PathVariable("id") Integer id, @RequestBody User user) {
    user.setId(id);
    this.userRepository.save(user);
  }


  private URI childLocation(StringBuffer parentUri, Object childId) {
    UriTemplate uri = new UriTemplate(parentUri.append("/{childId}").toString());
    return uri.expand(childId);
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Integer id) {
      super("User '" + id + "' not found.");
    }
  }
}
