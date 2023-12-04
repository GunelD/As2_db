import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class BookstoreApplication {

    private static final String URL = "jdbc:postgresql://localhost:5432/Bookstore";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Guni2009";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bookstore", "postgres", "Guni2009");
    }

    public static void main(String[] args) {
        try (Connection connection = getConnection()) {
            MetadataManager.showTableInfo(connection);
            MetadataManager.showKeyInfo(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }}
