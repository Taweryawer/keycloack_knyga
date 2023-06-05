package service.impl;

import dao.BookDao;
import dto.BookDto;
import dto.BookHolderDto;
import filter.BookFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.BookService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    public BookHolderDto getAllBooks(BookFilter bookFilter) {
        List<BookDto> books = bookDao.findAll(bookFilter).stream().map(BookDto::new).collect(Collectors.toList());
        return new BookHolderDto(bookDao.count(bookFilter), books);
    }

    @Override
    public Optional<BookDto> getBookById(Long id) {
        return bookDao.getById(id).map(BookDto::new);
    }
}
