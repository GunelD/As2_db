public class Book {
    private int bookId;
    private String title;
    private int quantityInStock;
    private double price;
    private String publishDate;
    private String genre;
    private String description;

    public Book(int bookId, String title, int quantityInStock, double price, String publishDate, String genre, String description) {
        this.bookId = bookId;
        this.title = title;
        this.quantityInStock = quantityInStock;
        this.price = price;
        this.publishDate = publishDate;
        this.genre = genre;
        this.description = description;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", quantityInStock=" + quantityInStock +
                ", price=" + price +
                ", publishDate='" + publishDate + '\'' +
                ", genre='" + genre + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
