import java.sql.Date;

public class Order {
    private String orderId;
    private String customerId;
    private int quantityOrdered;
    private double totalAmount;

    public Order(String orderId, String customerId, String bookId, int quantityOrdered, double totalAmount) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.quantityOrdered = quantityOrdered;
        this.totalAmount = totalAmount;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public int getQuantityOrdered() {
        return quantityOrdered;
    }

    public void setQuantityOrdered(int quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", quantityOrdered=" + quantityOrdered +
                ", totalAmount=" + totalAmount +
                '}';
    }
}