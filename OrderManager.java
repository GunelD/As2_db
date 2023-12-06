import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderManager {

    public static void createOrder(Connection connection, Order order) {
        try {
            connection.setAutoCommit(false);

            // Insert order into the Orders table
            String createOrderQuery = "INSERT INTO Orders VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement orderStatement = connection.prepareStatement(createOrderQuery)) {
                orderStatement.setString(1, order.getOrderId());
                orderStatement.setString(2, order.getCustomerId());
                orderStatement.setString(3, order.getOrderDate());
                orderStatement.setInt(4, order.getQuantityOrdered());
                orderStatement.setDouble(5, order.getTotalAmount());
                orderStatement.executeUpdate();
            }

            connection.commit();
            System.out.println("Order created successfully.");

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static Order getOrder(Connection connection, String orderId) throws SQLException {
        String selectOrderQuery = "SELECT * FROM Orders WHERE orderId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectOrderQuery)) {
            preparedStatement.setString(1, orderId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String customerId = resultSet.getString("customerId");
                    String orderDate = resultSet.getString("orderDate");
                    int quantityOrdered = resultSet.getInt("quantityOrdered");
                    double totalAmount = resultSet.getDouble("totalAmount");
                    return new Order(orderId, customerId, orderDate, quantityOrdered, totalAmount);
                } else {
                    System.out.println("Order not found.");
                    return null;
                }
            }
        }
    }

    public static void deleteOrder(Connection connection, String orderId) {
        try {
            connection.setAutoCommit(false);

            // Delete order from the Orders table
            String deleteOrderQuery = "DELETE FROM Orders WHERE orderId = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteOrderQuery)) {
                preparedStatement.setString(1, orderId);
                preparedStatement.executeUpdate();
            }

            connection.commit();
            System.out.println("Order deleted successfully.");

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}




