package SimpleStore.relations;

import SimpleStore.MySQLConnection;
import SimpleStore.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartItems {

    private final Carts carts = new Carts();

    public void createTable() throws SQLException {
        try (Connection conn = MySQLConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.addBatch("DROP TABLE IF EXISTS CartItems");
            stmt.addBatch("""
                CREATE TABLE CartItems (
                    cart_id     INT NOT NULL,
                    product_id  INT NOT NULL,
                    quantity    INT NOT NULL,
                    FOREIGN KEY (cart_id) REFERENCES Carts(cart_id),
                    FOREIGN KEY (product_id) REFERENCES Products(product_id),
                    PRIMARY KEY(cart_id, product_id)
                )
                """);
            stmt.executeBatch();
        }
    }

    public void insert(int user_id, int product_id, int quantity) throws SQLException {
        String sql = "INSERT INTO CartItems (cart_id, product_id, quantity) VALUES (?, ?, ?)";
        int cart_id = carts.findCartId(user_id);
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cart_id);
            ps.setInt(2, product_id);
            ps.setInt(3, quantity);
            ps.executeUpdate();
        }
    }

    public List<Product> getCartItems(int cart_id) throws SQLException {
        String sql = "SELECT c.product_id, name, price, c.quantity, image FROM CartItems c INNER JOIN Products p ON c.product_id=p.product_id WHERE cart_id = ?";
        try (Connection conn = MySQLConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, cart_id);
            ResultSet rs = stmt.executeQuery();
            List<Product> cartItems = new ArrayList<>();
            while (rs.next()) {
                int product_id = rs.getInt("product_id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int qty = rs.getInt("quantity");
                String image = rs.getString("image");
                cartItems.add(new Product(product_id, name, price, qty, image));
            }
            rs.close();
            stmt.close();
            return cartItems;
        }
    }
}