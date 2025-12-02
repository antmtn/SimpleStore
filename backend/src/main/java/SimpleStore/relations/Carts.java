package SimpleStore.relations;

import SimpleStore.MySQLConnection;
import java.sql.*;
import java.sql.SQLException;

public class Carts {
    public void createTable() throws SQLException {
        try (Connection conn = MySQLConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.addBatch("DROP TABLE IF EXISTS Carts");
            stmt.addBatch("""
                CREATE TABLE Carts (
                    cart_id     INT PRIMARY KEY AUTO_INCREMENT,
                    user_id     INT REFERENCES Users(user_id)
                )
                """);
            stmt.executeBatch();
        }
    }

    public int findCartId(int user_id) throws SQLException {
        String findIdSQL = "SELECT cart_id FROM Carts WHERE user_id = ?";
        try (Connection conn = MySQLConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(findIdSQL);
            stmt.setInt(1, user_id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("cart_id");
            }
            else {
                String addNewCartSQL = "INSERT INTO Carts (user_id) VALUES (?)";
                PreparedStatement addNewCartPreparedStatement = conn.prepareStatement(addNewCartSQL, Statement.RETURN_GENERATED_KEYS);
                addNewCartPreparedStatement.setInt(1, user_id);
                addNewCartPreparedStatement.executeUpdate();
                ResultSet rsNew = addNewCartPreparedStatement.getGeneratedKeys();
                if (rsNew.next()) {
                    return rsNew.getInt(1);
                }
            }
            return 0;
        }
    }
}