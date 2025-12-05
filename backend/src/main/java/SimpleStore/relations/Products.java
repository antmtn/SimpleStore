package SimpleStore.relations;

import SimpleStore.MySQLConnection;
import SimpleStore.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Products {

    public void createTable() throws SQLException {
        try (Connection conn = MySQLConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("""
                CREATE TABLE Products (
                    product_id INT  NOT NULL AUTO_INCREMENT PRIMARY KEY,
                    name       VARCHAR(50) NOT NULL,
                    price      DECIMAL(8,2) NOT NULL,
                    quantity   INT UNSIGNED NOT NULL,
                    image      VARCHAR(255) NOT NULL
                )
                """);
//            stmt.executeBatch();
        }
    }

    public void deleteTable() throws SQLException {
        try (Connection conn = MySQLConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("DROP TABLE IF EXISTS Products");
        }
    }

    public int insert(String name, double price, int qty, String image) throws SQLException {
        String sql = "INSERT INTO Products (name, price, quantity, image) VALUES (?,?,?,?)";
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.setInt(3, qty);
            ps.setString(4, image);
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

            throw new SQLException("Failed to insert product");
        }
    }

    public String getNames() throws SQLException {
        try (Connection conn = MySQLConnection.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT product_id, name FROM Products");
            // Display Query results
            StringBuilder sb = new StringBuilder();
            while (rs.next()) {
                sb.append("(").append(rs.getInt("product_id")).append(", ");
                sb.append(rs.getString("name") + ") ");
            }
            rs.close();
            stmt.close();
            return sb.toString();
        }
    }

    public List<Product> getAll() throws SQLException {
        try (Connection conn = MySQLConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Products");
            ResultSet rs = stmt.executeQuery();
            List<Product> products = new ArrayList<>();
            while (rs.next()) {
                int product_id = rs.getInt("product_id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int qty = rs.getInt("quantity");
                String image = rs.getString("image");
                products.add(new Product(product_id, name, price, qty, image));
            }
            rs.close();
            stmt.close();
            return products;
        }
    }

    public List<Product> getUnderPrice(double maxPrice) throws SQLException {
        String sql = "SELECT * FROM Products WHERE price <= ?";
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, maxPrice);
            ResultSet rs = stmt.executeQuery();

            List<Product> products = new ArrayList<>();
            while (rs.next()) {
                int product_id = rs.getInt("product_id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int qty = rs.getInt("quantity");
                String image = rs.getString("image");
                products.add(new Product(product_id, name, price, qty, image));
            }
            rs.close();
            stmt.close();
            return products;
        }
    }

}
