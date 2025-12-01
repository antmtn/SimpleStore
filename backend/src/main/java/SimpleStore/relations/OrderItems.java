package SimpleStore.relations;

import SimpleStore.MySQLConnection;
import SimpleStore.model.OrderItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItems {

    public void createTable() throws SQLException {
        try (Connection conn = MySQLConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.addBatch("DROP TABLE IF EXISTS OrderItems");
            stmt.addBatch("""
                CREATE TABLE OrderItems (
                    OrderId INTEGER NOT NULL,
                    ProductId INTEGER NOT NULL,
                    Quantity TINYINT UNSIGNED NOT NULL,
                    PRIMARY KEY (OrderId, ProductId),
                    FOREIGN KEY (OrderId) REFERENCES Orders(OrderId),
                    FOREIGN KEY (ProductId) REFERENCES Products(product_id)
                )
                """);
            stmt.executeBatch();
        }
    }

    public void insert(int orderId, int productId, int quantity) throws SQLException {
        String sql = "INSERT INTO OrderItems (OrderId, ProductId, Quantity) VALUES (?, ?, ?)";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderId);
            ps.setInt(2, productId);
            ps.setInt(3, quantity);

            ps.executeUpdate();
        }
    }

    public List<OrderItem> getOrderItems(int orderId) throws SQLException {
        String sql = "SELECT * FROM OrderItems WHERE OrderId = ?";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, orderId);

            List<OrderItem> items = new ArrayList<>();
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    items.add(new OrderItem(
                            rs.getInt("OrderId"),
                            rs.getInt("ProductId"),
                            rs.getInt("Quantity")
                    ));
                }
                return items;
            }
        }
    }

    public List<OrderItem> getAllOrderItems() throws SQLException {
        try (Connection conn = MySQLConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM OrderItems");
            ResultSet rs = stmt.executeQuery();
            List<OrderItem> items = new ArrayList<>();
            while (rs.next()) {
                int orderID = rs.getInt("OrderId");
                int productId = rs.getInt("ProductId");
                int quantity = rs.getInt("Quantity");
                items.add(new OrderItem(orderID, productId, quantity));
            }
            rs.close();
            stmt.close();
            return items;
        }
    }

}
