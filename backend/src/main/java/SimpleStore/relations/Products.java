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
            stmt.addBatch("DROP TABLE IF EXISTS Products");
            stmt.addBatch("""
                CREATE TABLE Products (
                    product_id INT  NOT NULL AUTO_INCREMENT PRIMARY KEY,
                    name       VARCHAR(255) NOT NULL,
                    price      DECIMAL(10,2) NOT NULL,
                    quantity   INT NOT NULL,
                    image      VARCHAR(255) NOT NULL
                )
                """);
            stmt.executeBatch();
        }
    }

    public int insert(String name, double price, int qty, String image) throws SQLException {
        String sql = "INSERT INTO products (name, price, quantity, image) VALUES (?,?,?,?)";
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.setInt(3, qty);
            ps.setString(4, image);
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

}
