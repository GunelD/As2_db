import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookAuthorManager {

    public static void CreateBookWithAuthor(Connection connection, Integer bookId, String authorId) throws SQLException {
        String createQuery = "INSERT INTO Book_Author VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(createQuery)) {
            preparedStatement.setInt(1, bookId);
            preparedStatement.setString(2, authorId);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Book linked to author successfully.");
            } else {
                System.out.println("Failed to link book to author.");
            }
        }
    }

    public static void DeleteBookWithAuthor(Connection connection, Integer bookId, String authorId) throws SQLException {
        String deleteQuery = "DELETE FROM Book_Author WHERE bookId = ? AND authorId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, bookId);
            preparedStatement.setString(2, authorId);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Book unlinked from author successfully.");
            } else {
                System.out.println("Failed to unlink book from author.");
            }
        }
    }
}
