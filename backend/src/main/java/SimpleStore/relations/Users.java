package SimpleStore.relations;

import SimpleStore.MySQLConnection;
import java.sql.*;
import java.sql.SQLException;

public class Users {

    // Creates the Users table if needed
    public void createTable() throws SQLException {
        try (Connection conn = MySQLConnection.getConnection();
             Statement stmt = conn.createStatement()) {
//            stmt.addBatch("DROP TABLE IF EXISTS Users");
            stmt.execute("""
                CREATE TABLE Users (
                    user_id     INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                    is_admin    BOOLEAN DEFAULT FALSE,
                    username    VARCHAR(255) NOT NULL UNIQUE,
                    password    VARCHAR(255) NOT NULL
                )
                """);
//            stmt.executeBatch();
        }
    }

    // Deletes the Users table if needed
    public void deleteTable() throws SQLException {
        try (Connection conn = MySQLConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("DROP TABLE IF EXISTS Users");
        }
    }

    // Inserts a new tuple with given username and password into the Users table
    // Used when users sign up 
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

    // Used when users login
    // Checks if there is a user that uses that username and password
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

    // Inserts a new user into the table if the username doesn't already exist
    // Used for sign up 
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

    // Used for debugging
    // Gets all of the usernames 
    public String getUsernames() throws SQLException {
        try (Connection conn = MySQLConnection.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT user_id, username FROM Users");
            // Display Query results
            StringBuilder sb = new StringBuilder();
            while (rs.next()) {
                sb.append("(" + rs.getString("user_id") + ", " + rs.getString("username") + ") ");
            }
            rs.close();
            stmt.close();
            return sb.toString();
        }
    }
}