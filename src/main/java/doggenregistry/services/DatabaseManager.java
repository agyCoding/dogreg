package doggenregistry.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// VIL IKKE KOBLE SEG TIL DATABASEN
public class DatabaseManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/dog_gen_register_db";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "M!nDB2024";

    public static Connection getConnection() throws SQLException {
        try {
            System.out.println("Loading MySQL JDBC Driver...");
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("MySQL JDBC driver not found", e);
        }
    
        try {
            System.out.println("Attempting to connect to database...");
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("Connection successful!");
            return connection;
        } catch (SQLException e) {
            System.err.println("Error: Unable to connect to the database");
            System.err.println("SQLException: " + e.getMessage());
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("VendorError: " + e.getErrorCode());
            throw e;
        }
    }
}
