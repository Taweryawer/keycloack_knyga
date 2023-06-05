package dao;

import domain.Book;
import domain.User;
import exception.DaoException;
import filter.BookFilter;

import java.util.List;
import java.util.Optional;

public interface BookDao {

    List<Book> findAll(BookFilter bookFilter);

    Optional<Book> getById(Long id);

    Long count(BookFilter bookFilter);
}
