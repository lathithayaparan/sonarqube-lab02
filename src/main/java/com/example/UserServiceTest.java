package test.java.com.example;

import main.java.com.example.UserService;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    @Test
    public void testFindUser() {
        UserService service = new UserService();
        assertThrows(SQLException.class, () -> service.findUser("test"));
    }

    @Test
    public void testDeleteUser() {
        UserService service = new UserService();
        assertThrows(SQLException.class, () -> service.deleteUser("test"));
    }
}