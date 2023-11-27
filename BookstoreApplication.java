import java.sql.*;

public class BookstoreApplication {
    private static final String URL = "jdbc:postgresql://localhost:5432/Bookstore";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Guni2009";

    private static Connection connection;

    public static void main(String[] args) {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bookstore","postgres","Guni2009");

            createTableExample();
            insertDataExample();
            fetchDataExample();

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

    private static void createTableExample() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS Authors (" +
                    "author_id VARCHAR(30) PRIMARY KEY," +
                    "author_name VARCHAR(100) NOT NULL)");

            statement.execute("CREATE TABLE IF NOT EXISTS Books (" +
                    "book_id VARCHAR(30) PRIMARY KEY," +
                    "title VARCHAR(255) NOT NULL," +
                    "quantity_in_stock INTEGER NOT NULL CHECK (quantity_in_stock >= 0)," +
                    "price NUMERIC(10, 2) NOT NULL," +
                    "author_id VARCHAR(30) REFERENCES Authors ON DELETE CASCADE," +
                    "publish_date DATE," +
                    "genre VARCHAR(100)," +
                    "description TEXT)");

            statement.execute("CREATE TABLE IF NOT EXISTS Customers (" +
                    "customer_id VARCHAR(30) PRIMARY KEY," +
                    "customer_name VARCHAR(100) NOT NULL," +
                    "email VARCHAR(255) UNIQUE," +
                    "phone_number VARCHAR(20))");

            statement.execute("CREATE TABLE IF NOT EXISTS Orders (" +
                    "order_id VARCHAR(30) PRIMARY KEY," +
                    "customer_id VARCHAR(30) REFERENCES Customers," +
                    "order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                    "book_id VARCHAR(30) REFERENCES Books," +
                    "quantity_ordered INTEGER NOT NULL," +
                    "total_amount DECIMAL(10, 2) NOT NULL)");
        }
    }

    private static void insertDataExample() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("INSERT INTO Authors (author_id, author_name) VALUES " +
                    "('1', 'Author1')," +
                    "('2', 'Author2')");

            statement.execute("INSERT INTO Books (book_id, title, quantity_in_stock, price, author_id, publish_date, genre, description) VALUES " +
                    "('101', 'Book1', 50, 19.99, '1', '2022-01-01', 'Fiction', 'Description1')," +
                    "('102', 'Book2', 30, 29.99, '2', '2022-02-01', 'Non-Fiction', 'Description2')");

            statement.execute("INSERT INTO Customers (customer_id, customer_name, email, phone_number) VALUES " +
                    "('C001', 'Customer1', 'customer1@example.com', '123456789')," +
                    "('C002', 'Customer2', 'customer2@example.com', '987654321')");

            statement.execute("INSERT INTO Orders (order_id, customer_id, book_id, quantity_ordered, total_amount) VALUES " +
                    "('O001', 'C001', '101', 2, 39.98)," +
                    "('O002', 'C002', '102', 1, 29.99)");
        }
    }

    private static void fetchDataExample() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            ResultSet authorsResult = statement.executeQuery("SELECT * FROM Authors");
            printResultSet(authorsResult, "Authors");

            ResultSet booksResult = statement.executeQuery("SELECT * FROM Books");
            printResultSet(booksResult, "Books");

            ResultSet customersResult = statement.executeQuery("SELECT * FROM Customers");
            printResultSet(customersResult, "Customers");

            ResultSet ordersResult = statement.executeQuery("SELECT * FROM Orders");
            printResultSet(ordersResult, "Orders");
        }
    }

    private static void printResultSet(ResultSet resultSet, String tableName) throws SQLException {
        System.out.println("\nData from " + tableName + " table:");
        while (resultSet.next()) {
            System.out.println("Row: " + resultSet.getString(1) + " | " + resultSet.getString(2));
        }
    }
}

