import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class BookstoreApplication {

    private static final String URL = "jdbc:postgresql://localhost:5432/Bookstore";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Guni2009";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bookstore","postgres","Guni2009");
    }

    public static void main(String[] args) {
        try (Connection connection = getConnection()) {
            Author author = new Author("A001", "John Doe");
            AuthorManager.createAuthor(connection, author);

            Author retrievedAuthor = AuthorManager.getAuthor(connection, "A001");
            System.out.println("Retrieved Author: " + retrievedAuthor);

            AuthorManager.updateAuthor(connection, "A001", "John Doe Updated");
            retrievedAuthor = AuthorManager.getAuthor(connection, "A001");
            System.out.println("Updated Author: " + retrievedAuthor);

            AuthorManager.deleteAuthor(connection, "A001");

            Book book = new Book("B001", "Sample Book", 10, 19.99, "2023-01-01", "Fiction", "A sample book description");
            BookManager.createBook(connection, book);

            List<Book> books = BookManager.getBooks(connection);
            System.out.println("Retrieved Books: " + books);

            Book retrievedBook = books.get(0);
            retrievedBook.setTitle("Updated Book Title");
            BookManager.updateBook(connection, retrievedBook);

            books = BookManager.getBooks(connection);
            System.out.println("Updated Books: " + books);

            BookManager.deleteBook(connection, "B001");

            CustomerManager.createCustomer(connection, "C001", "Alice", "alice@example.com");

            Customer retrievedCustomer = CustomerManager.getCustomer(connection, "C001");
            System.out.println("Retrieved Customer: " + retrievedCustomer);

            CustomerManager.updateCustomer(connection, "C001", "Alice Updated", "alice.updated@example.com");
            retrievedCustomer = CustomerManager.getCustomer(connection, "C001");
            System.out.println("Updated Customer: " + retrievedCustomer);

            CustomerManager.deleteCustomer(connection, "C001");

            Order order = new Order("O001", "C002", "2023-01-15", 3, 59.97);
            OrderManager.createOrder(connection, order);

            Order retrievedOrder = OrderManager.getOrder(connection, "O001");
            System.out.println("Retrieved Order: " + retrievedOrder);

            OrderManager.deleteOrder(connection, "O001");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
