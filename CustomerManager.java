import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerManager {

    public static void createCustomer(Connection connection, String id, String name, String email) throws SQLException {
        String insertCustomerQuery = "INSERT INTO Customers VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertCustomerQuery)) {
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, email);
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Customer created successfully.");
            } else {
                System.out.println("Failed to create customer.");
            }
        }
    }

    public static Customer getCustomer(Connection connection, String customerId) throws SQLException {
        String selectCustomerQuery = "SELECT * FROM Customers WHERE customerId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectCustomerQuery)) {
            preparedStatement.setString(1, customerId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String id = resultSet.getString("customerId");
                    String name = resultSet.getString("customerName");
                    String email = resultSet.getString("email");
                    return new Customer(id, name, email);
                } else {
                    System.out.println("Customer not found.");
                    return null;
                }
            }
        }
    }

    public static void updateCustomer(Connection connection, String customerId, String newName, String newEmail) throws SQLException {
        String updateCustomerQuery = "UPDATE Customers SET customerName = ?, email = ? WHERE customerId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateCustomerQuery)) {
            preparedStatement.setString(1, newName);
            preparedStatement.setString(2, newEmail);
            preparedStatement.setString(3, customerId);
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Customer updated successfully.");
            } else {
                System.out.println("Customer not found.");
            }
        }
    }

    public static void deleteCustomer(Connection connection, String customerId) throws SQLException {
        String deleteCustomerQuery = "DELETE FROM Customers WHERE customerId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteCustomerQuery)) {
            preparedStatement.setString(1, customerId);
            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Customer deleted successfully.");
            } else {
                System.out.println("Customer not found.");
            }
        }
    }
}
