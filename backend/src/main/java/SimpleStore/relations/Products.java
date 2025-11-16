package SimpleStore.relations;

import SimpleStore.MySQLConnection;

import java.sql.*;

public class Products {

    public void createTable() throws SQLException {
        try (Connection conn = MySQLConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.addBatch("DROP TABLE IF EXISTS Products");
            stmt.addBatch("""
                CREATE TABLE Products (
                    product_id VARCHAR(10)  NOT NULL PRIMARY KEY,
                    name       VARCHAR(255) NOT NULL,
                    price      DECIMAL(10,2) NOT NULL,
                    quantity   INT NOT NULL
                )
                """);
            stmt.executeBatch();
        }
    }

    public int insert(String id, String name, double price, int qty) throws SQLException {
        String sql = "INSERT INTO products (product_id, name, price, quantity) VALUES (?,?,?,?)";
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.setString(2, name);
            ps.setDouble(3, price);
            ps.setInt(4, qty);
            return ps.executeUpdate();
        }
    }

    public String getNames() throws SQLException {
        try (Connection conn = MySQLConnection.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT name FROM Products");
            // Display Query results
            StringBuilder sb = new StringBuilder();
            while (rs.next()) {
                sb.append(rs.getString("name") + "\n");
            }
            rs.close();
            stmt.close();
            return sb.toString();
        }
    }

}
