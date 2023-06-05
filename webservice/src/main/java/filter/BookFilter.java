package filter;

public class BookFilter {

    private String name;

    private int pageNumber;
    private int pageSize;
    private String sortBy;
    private boolean asc;

    private BookFilter() {
    }

    public static BookFilter create() {
        return new BookFilter();
    }

    public String getName() {
        return name;
    }

    public BookFilter setName(String name) {
        this.name = name;
        return this;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public BookFilter setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
        return this;
    }

    public int getPageSize() {
        return pageSize;
    }

    public BookFilter setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public String getSortBy() {
        return sortBy;
    }

    public BookFilter setSortBy(String sortBy) {
        this.sortBy = sortBy;
        return this;
    }

    public boolean isAsc() {
        return asc;
    }

    public BookFilter setAsc(boolean asc) {
        this.asc = asc;
        return this;
    }
}
