package main.java.com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;

public class UserService {

    private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());
    private static final String DB_URL = "jdbc:mysql://localhost/db";
    private static final String DB_USER = "root";
    
    // FIXED: Use environment variable or config file instead of hardcoded password
    private String password = System.getenv("DB_PASSWORD");

    // FIXED: SQL Injection prevented with PreparedStatement
    // FIXED: Using try-with-resources to auto-close connections
    // FIXED: Specify exact columns instead of SELECT *
    public void findUser(String username) throws SQLException {
        String query = "SELECT id, name, email FROM users WHERE name = ?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, password);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, username);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                // Process results
                if (rs.next()) {
                    LOGGER.log(Level.INFO, "User found: {0}", username);
                }
            }
        }
    }

    // FIXED: SQL Injection prevented + proper resource management
    public void deleteUser(String username) throws SQLException {
        String query = "DELETE FROM users WHERE name = ?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, password);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, username);
            int rowsAffected = pstmt.executeUpdate();
            LOGGER.log(Level.INFO, "Deleted {0} user(s)", rowsAffected);
        }
    }
}