import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookOrderManager {

    public static void createBookInOrder(Connection connection, Integer bookId, String orderId) throws SQLException {
        String createQuery = "INSERT INTO Book_Order VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(createQuery)) {
            preparedStatement.setInt(1, bookId);
            preparedStatement.setString(2, orderId);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Book linked to order successfully.");
            } else {
                System.out.println("Failed to link book to order.");
            }
        }
    }

    public static void deleteBookFromOrder(Connection connection, Integer bookId, String orderId) throws SQLException {
        String deleteQuery = "DELETE FROM Book_Order WHERE bookId = ? AND orderId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, bookId);
            preparedStatement.setString(2, orderId);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Book unlinked from order successfully.");
            } else {
                System.out.println("Failed to unlink book from order.");
            }
        }
    }
}