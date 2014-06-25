package app.repository;

import java.util.List;

import app.domain.Book;

public interface BookRepository {

  Book findById(Integer id);

  List<Book> findAll();

  Book save(Book book);

  void delete(Integer id);
}
