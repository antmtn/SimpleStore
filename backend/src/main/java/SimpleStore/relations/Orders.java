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
//            stmt.addBatch("DROP TABLE IF EXISTS Orders");
            stmt.execute("""
                CREATE TABLE Orders (
                    order_id INT  NOT NULL AUTO_INCREMENT PRIMARY KEY,
                    user_id INT NOT NULL,
                    FOREIGN KEY (user_id) REFERENCES Users(user_id)
                )
                """);
//            stmt.executeBatch();
        }
    }

    public void deleteTable() throws SQLException {
        try (Connection conn = MySQLConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("DROP TABLE IF EXISTS Orders");
        }
    }

    public int insertOrder(int userId) throws SQLException {
        String sql = "INSERT INTO Orders (user_id) VALUES (?)";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, userId);
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

            throw new SQLException("Failed to insert order");
        }
    }

    // get all order of a user
    public List<Order> getOrders(int userId) throws SQLException {
        String sql = "SELECT * FROM Orders WHERE user_id = ?";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                List<Order> orders = new ArrayList<>();
                while (rs.next()) {
                    int orderID = rs.getInt("order_id");
                    int customerID = rs.getInt("user_id");
                    orders.add(new Order(orderID, customerID));
                }
                return orders;
            }
        }
    }

    // get all rows of table
    public List<Order> getAllOrders() throws SQLException {
        try (Connection conn = MySQLConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Orders");
            ResultSet rs = stmt.executeQuery();
            List<Order> orders = new ArrayList<>();
            while (rs.next()) {
                int orderID = rs.getInt("order_id");
                int userId = rs.getInt("user_id");
                orders.add(new Order(orderID, userId));
            }
            rs.close();
            stmt.close();
            return orders;
        }
    }

}
