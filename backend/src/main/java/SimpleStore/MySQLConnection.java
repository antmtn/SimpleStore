package SimpleStore;

import java.sql.*;

public class MySQLConnection {

    private static final String URL  = "jdbc:mysql://localhost:3306/simplestore_db?serverTimezone=UTC";
    private static final String USER = System.getenv().getOrDefault("DB_USER", "simplestore");
    private static final String PASSWORD = System.getenv().getOrDefault("DB_PASSWORD", "password");



    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
