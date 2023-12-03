import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookManager {

    public static void createBook(Connection connection, Book book) {
        String insertBookQuery = "INSERT INTO Books VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertBookQuery)) {
            preparedStatement.setString(1, book.getBookId());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setInt(3, book.getQuantityInStock());
            preparedStatement.setDouble(4, book.getPrice());
            preparedStatement.setString(5, book.getPublishDate());
            preparedStatement.setString(6, book.getGenre());
            preparedStatement.setString(7, book.getDescription());

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Book created successfully.");
            } else {
                System.out.println("Failed to create book.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Book> getBooks(Connection connection) {
        String selectBooksQuery = "SELECT b.*, a.authorName, c.customerName, o.orderDate " +
                "FROM Books b " +
                "LEFT JOIN Book_author ba ON b.bookId = ba.bookId " +
                "LEFT JOIN Authors a ON ba.authorId = a.authorId " +
                "LEFT JOIN Book_order bo ON b.bookId = bo.bookId " +
                "LEFT JOIN Orders o ON bo.orderId = o.orderId " +
                "LEFT JOIN Customers c ON o.customerId = c.customerId";

        List<Book> books = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectBooksQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Book book = mapResultSetToBook(resultSet);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    private static Book mapResultSetToBook(ResultSet resultSet) throws SQLException {
        String bookId = resultSet.getString("bookId");
        String title = resultSet.getString("title");
        int quantityInStock = resultSet.getInt("quantityInStock");
        double price = resultSet.getDouble("price");
        String publishDate = resultSet.getString("publishDate");
        String genre = resultSet.getString("genre");
        String description = resultSet.getString("description");
        String authorName = resultSet.getString("authorName");
        String customerName = resultSet.getString("customerName");
        String orderDate = resultSet.getString("orderDate");

        return new Book(bookId, title, quantityInStock, price, publishDate, genre, description);
    }

    public static void updateBook(Connection connection, Book book) {
        String updateBookQuery = "UPDATE Books SET title = ?, quantityInStock = ?, price = ?, " +
                "publishDate = ?, genre = ?, description = ? WHERE bookId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateBookQuery)) {
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setInt(2, book.getQuantityInStock());
            preparedStatement.setDouble(3, book.getPrice());
            preparedStatement.setString(4, book.getPublishDate());
            preparedStatement.setString(5, book.getGenre());
            preparedStatement.setString(6, book.getDescription());
            preparedStatement.setString(7, book.getBookId());

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Book updated successfully.");
            } else {
                System.out.println("Book not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteBook(Connection connection, String bookId) {
        String deleteBookQuery = "DELETE FROM Books WHERE bookId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteBookQuery)) {
            preparedStatement.setString(1, bookId);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Book deleted successfully.");
            } else {
                System.out.println("Book not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
