package SimpleStore.relations;

import SimpleStore.MySQLConnection;
import SimpleStore.model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Orders {

    public void createTable() throws SQLException {
        try (Connection conn = MySQLConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.addBatch("DROP TABLE IF EXISTS Orders");
            stmt.addBatch("""
                CREATE TABLE Orders (
                    OrderId INT  NOT NULL AUTO_INCREMENT PRIMARY KEY,
                    CustomerId INT NOT NULL,
                    FOREIGN KEY (CustomerId) REFERENCES Customers(CustomerId)
                )
                """);
            stmt.executeBatch();
        }
    }

    public int insertOrder(int customerId) throws SQLException {
        String sql = "INSERT INTO Orders (CustomerId) VALUES (?)";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, customerId);
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        throw new SQLException("Failed to insert order");
        }
    }

    public List<Order> getOrders(int customerId) throws SQLException {
        String sql = "SELECT * FROM Orders WHERE CustomerId = ?";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customerId);

            try (ResultSet rs = stmt.executeQuery()) {
                List<Order> orders = new ArrayList<>();
                while (rs.next()) {
                    int orderID = rs.getInt("OrderId");
                    int customerID = rs.getInt("CustomerId");
                    orders.add(new Order(orderID, customerID));
                }
                return orders;
            }
        }
    }

    public List<Order> getAllOrders() throws SQLException {
        try (Connection conn = MySQLConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Orders");
            ResultSet rs = stmt.executeQuery();
            List<Order> orders = new ArrayList<>();
            while (rs.next()) {
                int orderID = rs.getInt("OrderId");
                int customerID = rs.getInt("CustomerId");
                orders.add(new Order(orderID, customerID));
            }
            rs.close();
            stmt.close();
            return orders;
        }
    }

}
