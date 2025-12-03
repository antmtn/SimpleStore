package SimpleStore.relations;

import SimpleStore.MySQLConnection;
import SimpleStore.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CartItems {

    private final Carts carts = new Carts();

    public void createTable() throws SQLException {
        try (Connection conn = MySQLConnection.getConnection();
             Statement stmt = conn.createStatement()) {
//            stmt.addBatch("DROP TABLE IF EXISTS CartItems");
            stmt.execute("""
                CREATE TABLE CartItems (
                    cart_id     INT NOT NULL,
                    product_id  INT NOT NULL,
                    quantity    TINYINT UNSIGNED NOT NULL,
                    FOREIGN KEY (cart_id) REFERENCES Carts(cart_id),
                    FOREIGN KEY (product_id) REFERENCES Products(product_id),
                    PRIMARY KEY(cart_id, product_id)
                )
                """);
//            stmt.executeBatch();
        }
    }

    public void deleteTable() throws SQLException {
        try (Connection conn = MySQLConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("DROP TABLE IF EXISTS CartItems");
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

    public int updateItemQuantity(int user_id, int productId, int newQuantity) throws SQLException {
        //System.out.println("Update Request Received " + user_id + " " + productId + " " + newQuantity);
        String sql = "UPDATE CartItems SET quantity = ? WHERE cart_id = ? AND product_id = ?";
        int cartId = carts.findCartId(user_id);
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, newQuantity);
            stmt.setInt(2, cartId);
            stmt.setInt(3, productId);

            return stmt.executeUpdate();
        }
    }

    public int deleteProduct(int user_id, int productId) throws SQLException {
        String sql = "DELETE FROM CartItems WHERE cart_id = ? AND product_id = ?";
        int cartId = carts.findCartId(user_id);

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, cartId);
            ps.setInt(2, productId);

            return ps.executeUpdate();
        }
    }

    // get the string of all cart id and its products
    public String getCartNItems() throws SQLException {
        try (Connection conn = MySQLConnection.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT cart_id, product_id FROM CartItems");
            // Display Query results
            HashMap<Integer, ArrayList<Integer>> ids = new HashMap<>();
            while (rs.next()) {
                int cartId = rs.getInt("cart_id");
                int productId = rs.getInt("product_id");
                ids.computeIfAbsent(cartId, k -> new ArrayList<>()).add(productId);
            }
            StringBuilder sb = new StringBuilder();
            for (Integer id : ids.keySet()) {
                sb.append("(").append(id).append(" - ");
                for (Integer p : ids.get(id))
                    sb.append(p).append(" ");
                sb.append(") <br>");
            }
            rs.close();
            stmt.close();
            return sb.toString();
        }
    }
}