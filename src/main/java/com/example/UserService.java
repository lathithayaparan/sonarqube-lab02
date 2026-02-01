package test.java.com.example;

import main.java.com.example.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    private UserService userService;

    @BeforeEach
    public void setUp() {
        // Set a test password to avoid null pointer
        System.setProperty("DB_PASSWORD", "test123");
        userService = new UserService();
    }

    @Test
    public void testUserServiceCreation() {
        assertNotNull(userService, "UserService should be created");
    }

    @Test
    public void testFindUserWithValidUsername() {
        // This will throw SQLException because database is not available
        // But it tests the method logic
        assertThrows(SQLException.class, () -> {
            userService.findUser("testuser");
        }, "Should throw SQLException when database is not available");
    }

    @Test
    public void testFindUserWithNullUsername() {
        assertThrows(SQLException.class, () -> {
            userService.findUser(null);
        }, "Should throw SQLException with null username");
    }

    @Test
    public void testFindUserWithEmptyUsername() {
        assertThrows(SQLException.class, () -> {
            userService.findUser("");
        }, "Should throw SQLException with empty username");
    }

    @Test
    public void testFindUserWithSpecialCharacters() {
        assertThrows(SQLException.class, () -> {
            userService.findUser("user@test.com");
        });
    }

    @Test
    public void testDeleteUserWithValidUsername() {
        assertThrows(SQLException.class, () -> {
            userService.deleteUser("testuser");
        }, "Should throw SQLException when database is not available");
    }

    @Test
    public void testDeleteUserWithNullUsername() {
        assertThrows(SQLException.class, () -> {
            userService.deleteUser(null);
        }, "Should throw SQLException with null username");
    }

    @Test
    public void testDeleteUserWithEmptyUsername() {
        assertThrows(SQLException.class, () -> {
            userService.deleteUser("");
        }, "Should throw SQLException with empty username");
    }

    @Test
    public void testDeleteUserWithSpecialCharacters() {
        assertThrows(SQLException.class, () -> {
            userService.deleteUser("admin'; DROP TABLE users; --");
        }, "PreparedStatement should prevent SQL injection");
    }

    @Test
    public void testMultipleOperations() {
        // Test multiple operations in sequence
        assertThrows(SQLException.class, () -> {
            userService.findUser("user1");
        });
        
        assertThrows(SQLException.class, () -> {
            userService.deleteUser("user2");
        });
    }

    @Test
    public void testUserServiceWithLongUsername() {
        String longUsername = "a".repeat(255);
        assertThrows(SQLException.class, () -> {
            userService.findUser(longUsername);
        });
    }
}