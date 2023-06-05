package service;

import dto.BookDto;
import dto.BookHolderDto;
import filter.BookFilter;

import java.util.Optional;

public interface BookService {

    BookHolderDto getAllBooks(BookFilter bookFilter);

    Optional<BookDto> getBookById(Long id);
}
