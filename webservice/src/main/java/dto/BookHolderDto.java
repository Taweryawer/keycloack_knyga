package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookHolderDto {

    private final Long count;
    private final List<BookDto> bookDtos;

    public BookHolderDto(Long count, List<BookDto> bookDtos) {
        this.count = count;
        this.bookDtos = bookDtos;
    }

    public Long getCount() {
        return count;
    }

    public List<BookDto> getBookDtos() {
        return bookDtos;
    }
}
