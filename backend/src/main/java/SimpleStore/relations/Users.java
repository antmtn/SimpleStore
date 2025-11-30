package SimpleStore.relations;

import SimpleStore.MySQLConnection;
import java.sql.*;
import java.sql.SQLException;

public class Users {
    public void createTable() throws SQLException {
        try (Connection conn = MySQLConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.addBatch("DROP TABLE IF EXISTS Users");
            stmt.addBatch("""
                CREATE TABLE Users (
                    user_id     INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                    is_admin    BOOLEAN DEFAULT FALSE,
                    username    VARCHAR(255) NOT NULL,
                    password    VARCHAR(255) NOT NULL
                )
                """);
            stmt.executeBatch();
        }
    }

    public int insert(String username, String password) throws SQLException {
        String sql = "INSERT INTO users (username, password) VALUES (?,?)";
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, username);
            ps.setString(2, password);

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

            return 0;
        }
    }

    public int checkLogin(String username, String password) throws SQLException {
        String sql = "SELECT user_id FROM users WHERE username = ? AND password = ?";
        try (Connection conn = MySQLConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
            return 0;
        }
    }

    public int checkSignup(String username, String password) throws SQLException {
        String sql = "SELECT user_id FROM users WHERE username = ?";
        try (Connection conn = MySQLConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()){
                if (!rs.next()) {
                    return insert(username, password);
                }
            }
            return 0;
        }
    }
}