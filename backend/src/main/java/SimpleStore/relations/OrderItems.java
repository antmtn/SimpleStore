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
//            stmt.addBatch("DROP TABLE IF EXISTS OrderItems");
            stmt.execute("""
                CREATE TABLE OrderItems (
                    order_id INTEGER NOT NULL,
                    product_id INTEGER NOT NULL,
                    quantity TINYINT UNSIGNED NOT NULL,
                    PRIMARY KEY (order_id, product_id),
                    FOREIGN KEY (order_id) REFERENCES Orders(order_id),
                    FOREIGN KEY (product_id) REFERENCES Products(product_id)
                )
                """);
//            stmt.executeBatch();
        }
    }

    public void deleteTable() throws SQLException {
        try (Connection conn = MySQLConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("DROP TABLE IF EXISTS OrderItems");
        }
    }

    public void insert(int orderId, int productId, int quantity) throws SQLException {
        String sql = "INSERT INTO OrderItems (order_id, product_id, quantity) VALUES (?, ?, ?)";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderId);
            ps.setInt(2, productId);
            ps.setInt(3, quantity);

            ps.executeUpdate();
        }
    }

    // get all order items of an order
    public List<OrderItem> getOrderItems(int orderId) throws SQLException {
        String sql = "SELECT * FROM OrderItems WHERE order_id = ?";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, orderId);

            List<OrderItem> items = new ArrayList<>();
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    items.add(new OrderItem(
                            rs.getInt("order_id"),
                            rs.getInt("product_id"),
                            rs.getInt("quantity")
                    ));
                }
                return items;
            }
        }
    }

    // get all rows of table
    public List<OrderItem> getAllOrderItems() throws SQLException {
        try (Connection conn = MySQLConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM OrderItems");
            ResultSet rs = stmt.executeQuery();
            List<OrderItem> items = new ArrayList<>();
            while (rs.next()) {
                int orderID = rs.getInt("order_id");
                int productId = rs.getInt("product_id");
                int quantity = rs.getInt("quantity");
                items.add(new OrderItem(orderID, productId, quantity));
            }
            rs.close();
            stmt.close();
            return items;
        }
    }

}
