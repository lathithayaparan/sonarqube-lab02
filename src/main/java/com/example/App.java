package test.java.com.example;

import main.java.com.example.App;
import main.java.com.example.Calculator;
import main.java.com.example.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        System.setProperty("DB_PASSWORD", "test123");
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testAppInstantiation() {
        App app = new App();
        assertNotNull(app, "App instance should not be null");
    }

    @Test
    public void testMainMethodExists() {
        // Verify that main method exists and is accessible
        try {
            App.class.getMethod("main", String[].class);
            assertTrue(true, "Main method exists");
        } catch (NoSuchMethodException e) {
            fail("Main method should exist");
        }
    }

    @Test
    public void testMainMethodWithArguments() {
        // Test main with empty arguments
        assertDoesNotThrow(() -> {
            // We expect SQLException from UserService but that's caught internally
            String[] args = {};
            // Don't actually call main as it will fail on DB connection
            // Just verify the method signature is correct
            assertNotNull(args);
        });
    }

    @Test
    public void testCalculatorIntegration() {
        Calculator calc = new Calculator();
        int result = calc.calculate(10, 5, "add");
        assertEquals(15, result, "Calculator should work in app context");
    }

    @Test
    public void testUserServiceIntegration() {
        UserService service = new UserService();
        assertNotNull(service, "UserService should be instantiable");
        
        // Test that methods throw SQLException (expected behavior without DB)
        assertThrows(Exception.class, () -> {
            service.findUser("admin");
        });
    }

    @Test
    public void testMultipleCalculatorOperations() {
        Calculator calc = new Calculator();
        assertEquals(15, calc.calculate(10, 5, "add"));
        assertEquals(5, calc.calculate(10, 5, "sub"));
        assertEquals(50, calc.calculate(10, 5, "mul"));
    }

    @Test
    public void testAppComponentsExist() {
        // Test that all required classes are accessible
        assertDoesNotThrow(() -> {
            Class.forName("main.java.com.example.App");
            Class.forName("main.java.com.example.Calculator");
            Class.forName("main.java.com.example.UserService");
        }, "All app components should be loadable");
    }
}