package springrest;

import dto.BookDto;
import dto.BookHolderDto;
import filter.BookFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.BookService;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(value = "/api/books", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public BookHolderDto getAllBooks(@RequestParam(required = false) String name,
                                     @RequestParam(required = false, defaultValue = "1") int page,
                                     @RequestParam(required = false, defaultValue = "5") int pageSize,
                                     @RequestParam(required = false, defaultValue = "id") String sortBy,
                                     @RequestParam(required = false, defaultValue = "true") boolean asc) {
        BookFilter bookFilter = BookFilter.create()
                .setName(name)
                .setPageSize(pageSize)
                .setPageNumber(page)
                .setSortBy(sortBy)
                .setAsc(asc);

        return bookService.getAllBooks(bookFilter);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public BookDto getBookById(@NotNull @PathVariable("id") Long id) {
        return bookService.getBookById(id).orElse(null);
    }
}
