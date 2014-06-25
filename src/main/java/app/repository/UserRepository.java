package app.repository;

import java.util.List;

import app.domain.User;

public interface UserRepository {

  User findById(Integer id);

  List<User> findAll();

  User save(User book);

  void delete(Integer id);
}
