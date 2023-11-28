import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

public class BookstoreApplication {
    private static final String URL = "jdbc:postgresql://localhost:5432/Bookstore";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Guni2009";

    private static Connection connection;

    public static void main(String[] args) {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bookstore", "postgres", "Guni2009");
            // Inserting new records
            insertTestData();

            // Retrieving all book information
            List<Book> books = DatabaseManage.retrieveAllBooks(connection);
            for (Book book : books) {
                System.out.println(book);
            }

            // Updating details of a book
            DatabaseManage.updateBookDescription(connection, "101", "New Description");

            // Removing an existing book
            DatabaseManage.removeBook(connection, "101");

            // handling exceptions
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void insertTestData() {
        try {
            DatabaseManage.insertAuthor(connection, new Author("1", "Author1"));
            DatabaseManage.insertBook(connection, new Book("101", "Book1", 50, 19.99, '2022-01-01', "Fiction", "Description1"));
            DatabaseManage.insertCustomer(connection, new Customer("C001", "Customer1", "customer1@example.com", "123456789"));
            DatabaseManage.insertOrder(connection, new Order("O001", "C001", "101", 2, 39.98));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

