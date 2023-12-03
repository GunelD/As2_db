import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class OrderManager {

    public static void createOrder(Connection connection, Order order) {
        try {
            connection.setAutoCommit(false); // Start a transaction

            if (isInventorySufficient(connection, order)) {
                insertOrder(connection, order);
                updateBooksInventory(connection, order, 1);
                connection.commit();
                System.out.println("Order placed successfully.");
            } else {
                connection.rollback();
                System.out.println("Failed to place order. Not enough books in the inventory.");
            }
        } catch (SQLException e) {
            try {
                connection.rollback(); // Rollback the transaction in case of exception
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true); // Reset auto-commit to true
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static boolean isInventorySufficient(Connection connection, Order order) throws SQLException {
        String checkInventoryQuery = "SELECT quantityInStock FROM Books WHERE bookId IN " +
                "(SELECT bookId FROM Book_order WHERE orderId = ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(checkInventoryQuery)) {
            preparedStatement.setString(1, order.getOrderId());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int quantityInStock = resultSet.getInt("quantityInStock");
                    int orderedQuantity = order.getQuantityOrdered();
                    if (quantityInStock < orderedQuantity) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static void insertOrder(Connection connection, Order order) throws SQLException {
        String insertOrderQuery = "INSERT INTO Orders VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertOrderQuery)) {
            preparedStatement.setString(1, order.getOrderId());
            preparedStatement.setString(2, order.getCustomerId());
            preparedStatement.setString(3, order.getOrderDate());
            preparedStatement.setInt(4, order.getQuantityOrdered());
            preparedStatement.setDouble(5, order.getTotalAmount());
            preparedStatement.executeUpdate();
        }
    }

    private static void updateBooksInventory(Connection connection, Order order, int quantityModifier) throws SQLException {
        String updateInventoryQuery = "UPDATE Books SET quantityInStock = quantityInStock + ? WHERE bookId IN " +
                "(SELECT bookId FROM Book_order WHERE orderId = ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateInventoryQuery)) {
            preparedStatement.setInt(1, quantityModifier * order.getQuantityOrdered());
            preparedStatement.setString(2, order.getOrderId());
            preparedStatement.executeUpdate();
        }
    }

    public static void deleteOrder(Connection connection, String orderId) {
        try {
            connection.setAutoCommit(false); // Start a transaction

            Order existingOrder = getOrder(connection, orderId);

            if (existingOrder != null) {
                deleteOrderDetails(connection, orderId);

                updateBooksInventory(connection, existingOrder, -1);

                connection.commit();
                System.out.println("Order deleted successfully.");
            } else {
                System.out.println("Order not found. Deletion failed.");
            }
        } catch (SQLException e) {
            try {
                connection.rollback(); // Rollback the transaction in case of exception
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true); // Reset auto-commit to true
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
                    String orderDate= resultSet.getString("orderDate");
                    int quantityOrdered = resultSet.getInt("quantityOrdered");
                    double totalAmount = resultSet.getDouble("totalAmount");

                    return new Order(orderId, customerId, orderDate, quantityOrdered, totalAmount);
                }
            }
        }
        return null;
    }

    private static String formatDate(java.sql.Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }



    private static void deleteOrderDetails(Connection connection, String orderId) throws SQLException {
        String deleteOrderQuery = "DELETE FROM Orders WHERE orderId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteOrderQuery)) {
            preparedStatement.setString(1, orderId);
            preparedStatement.executeUpdate();
        }
    }
}


