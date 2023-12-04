import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookManager {

    public static void createBook(Connection connection, Book book) {
        String insertBookQuery = "INSERT INTO Books VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertBookQuery)) {
            preparedStatement.setInt(1, book.getBookId());
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

    public static List<Object[]> getBooksData(Connection connection) {
        String selectBooksQuery = "SELECT b.*, a.*, c.*, o.* " +
                "FROM Books b " +
                "LEFT JOIN Book_author ba ON b.bookId = ba.bookId " +
                "LEFT JOIN Authors a ON ba.authorId = a.authorId " +
                "LEFT JOIN Book_order bo ON b.bookId = bo.bookId " +
                "LEFT JOIN Orders o ON bo.orderId = o.orderId " +
                "LEFT JOIN Customers c ON o.customerId = c.customerId";

        List<Object[]> booksData = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectBooksQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Object[] bookData = new Object[20];
                bookData[0] = resultSet.getInt("bookId");
                bookData[1] = resultSet.getString("title");
                bookData[2] = resultSet.getInt("quantityInStock");
                bookData[3] = resultSet.getDouble("price");
                bookData[4] = resultSet.getString("publishDate");
                bookData[5] = resultSet.getString("genre");
                bookData[6] = resultSet.getString("description");
                bookData[7] = resultSet.getString("authorId");
                bookData[8] = resultSet.getString("authorName");
                bookData[9] = resultSet.getString("customerId");
                bookData[10] = resultSet.getString("customerName");
                bookData[11] = resultSet.getString("email");
                bookData[12] = resultSet.getString("orderId");
                bookData[13] = resultSet.getString("orderDate");
                bookData[14] = resultSet.getInt("quantityOrdered");
                bookData[15] = resultSet.getDouble("totalAmount");

                booksData.add(bookData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return booksData;
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
            preparedStatement.setInt(7, book.getBookId());

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

    public static Book getBook(Connection connection, int bookId) throws SQLException {
        String selectBookQuery = "SELECT * FROM Books WHERE bookId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectBookQuery)) {
            preparedStatement.setInt(1, bookId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("bookId");
                    String title = resultSet.getString("title");
                    int quantityInStock = resultSet.getInt("quantityInStock");
                    double price = resultSet.getDouble("price");
                    String publishDate = resultSet.getString("publishDate");
                    String genre = resultSet.getString("genre");
                    String description = resultSet.getString("description");

                    return new Book(id, title, quantityInStock, price, publishDate, genre, description);
                } else {
                    System.out.println("Book not found.");
                    return null;
                }
            }
        }
    }


    public static void deleteBook(Connection connection, int bookId) {
        String deleteBookQuery = "DELETE FROM Books WHERE bookId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteBookQuery)) {
            preparedStatement.setInt(1, bookId);

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
