package main.java.com.example;

import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;

public class App {

    private static final Logger LOGGER = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        Calculator calc = new Calculator();

        // FIXED: Replaced System.out with proper logging
        LOGGER.log(Level.INFO, "Calculation result: {0}", calc.calculate(10, 5, "add"));

        UserService service = new UserService();
        
        // FIXED: Proper exception handling instead of generic throws Exception
        try {
            service.findUser("admin");
            service.deleteUser("admin");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database operation failed", e);
        }
    }
}