public class BookAuthor {
    private String bookId;
    private String authorId;

    public BookAuthor(String bookId, String authorId) {
        this.bookId = bookId;
        this.authorId = authorId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }
}

