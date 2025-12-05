package SimpleStore.relations;

import SimpleStore.MySQLConnection;
import SimpleStore.model.OrderItem;
import SimpleStore.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderItems {

    // This creates the table OrderItems 
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

    // This deletes the OrderItems table if needed
    public void deleteTable() throws SQLException {
        try (Connection conn = MySQLConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("DROP TABLE IF EXISTS OrderItems");
        }
    }

    // This inserts an order item into the table with the given orderId, productId, and quantity
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

    // This gets all of the products from a given order by doing an INNER JOIN on Products
    // where the order_id matches the given one. 
    public List<Product> getOrderProducts(int orderId) throws SQLException {
        String sql = "SELECT o.product_id, name, price, o.quantity, image FROM OrderItems o INNER JOIN Products p ON o.product_id=p.product_id WHERE order_id = ?";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, orderId);

            List<Product> items = new ArrayList<>();
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    items.add(new Product(
                            rs.getInt("product_id"),
                            rs.getString("name"),
                            rs.getDouble("price"),
                            rs.getInt("quantity"),
                            rs.getString("image")
                    ));
                }
                return items;
            }
        }
    }

    // used for debugging
    // get all rows of the table
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

    // used for debugging
    // get the string of all order id and its products + quantity
    public String getOrderNItemsQuantities() throws SQLException {
        try (Connection conn = MySQLConnection.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT order_id, product_id, quantity FROM OrderItems");
            // Display Query results
            HashMap<Integer, ArrayList<String>> items = new HashMap<>();
            while (rs.next()) {
                int orderId = rs.getInt("order_id");
                int productId = rs.getInt("product_id");
                int quantity = rs.getInt("quantity");

                String productQuantity = "(" + productId + "," + quantity + ")";
                items.computeIfAbsent(orderId, k -> new ArrayList<>()).add(productQuantity);
            }

            StringBuilder sb = new StringBuilder();
            for (Integer id : items.keySet()) {
                sb.append("(").append(id).append(" - ");
                for (String pq : items.get(id))
                    sb.append(pq).append(" ");
                sb.append(") <br>");
            }
            rs.close();
            stmt.close();
            return sb.toString();
        }
    }

}
