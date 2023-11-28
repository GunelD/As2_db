import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManage {

    static void insertAuthor(Connection connection, Author author) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Authors (author_id, author_name) VALUES (?, ?)")) {
            preparedStatement.setString(1, author.getAuthorId());
            preparedStatement.setString(2, author.getAuthorName());
            preparedStatement.executeUpdate();
        }
    }

    static void insertBook(Connection connection, Book book) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Books (book_id, title, quantity_in_stock, price, author_id, publish_date, genre, description) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {
            preparedStatement.setString(1, book.getBookId());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setInt(3, book.getQuantityInStock());
            preparedStatement.setDouble(4, book.getPrice());
            preparedStatement.setDate(6, book.getPublishDate());
            preparedStatement.setString(7, book.getGenre());
            preparedStatement.setString(8, book.getDescription());
            preparedStatement.executeUpdate();
        }
    }

    static void insertCustomer(Connection connection, Customer customer) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Customers (customer_id, customer_name, email, phone_number) VALUES (?, ?, ?, ?)")) {
            preparedStatement.setString(1, customer.getCustomerId());
            preparedStatement.setString(2, customer.getCustomerName());
            preparedStatement.setString(3, customer.getEmail());
            preparedStatement.setString(4, customer.getPhoneNumber());
            preparedStatement.executeUpdate();
        }
    }

