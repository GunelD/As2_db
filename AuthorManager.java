import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorManager {

    public static void createAuthor(Connection connection, Author author) throws SQLException {
        String insertAuthorQuery = "INSERT INTO Authors VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertAuthorQuery)) {
            preparedStatement.setString(1, author.getAuthorId());
            preparedStatement.setString(2, author.getAuthorName());

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Author created successfully.");
            } else {
                System.out.println("Failed to create author.");
            }
        }
    }

    public static Author getAuthor(Connection connection, String authorId) throws SQLException {
        String selectAuthorQuery = "SELECT * FROM Authors WHERE authorId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectAuthorQuery)) {
            preparedStatement.setString(1, authorId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String id = resultSet.getString("authorId");
                    String name = resultSet.getString("authorName");
                    return new Author(id, name);
                } else {
                    System.out.println("Author not found.");
                    return null;
                }
            }
        }
    }

    public static void updateAuthor(Connection connection, String authorId, String newAuthorName) throws SQLException {
        String updateAuthorQuery = "UPDATE Authors SET authorName = ? WHERE authorId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateAuthorQuery)) {
            preparedStatement.setString(1, newAuthorName);
            preparedStatement.setString(2, authorId);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Author updated successfully.");
            } else {
                System.out.println("Author not found.");
            }
        }
    }

    public static void deleteAuthor(Connection connection, String authorId) throws SQLException {
        String deleteAuthorQuery = "DELETE FROM Authors WHERE authorId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteAuthorQuery)) {
            preparedStatement.setString(1, authorId);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Author deleted successfully.");
            } else {
                System.out.println("Author not found.");
            }
        }
    }
}




