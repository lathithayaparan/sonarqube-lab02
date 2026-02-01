package test.java.com.example;

import main.java.com.example.Calculator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    @Test
    public void testCalculator() {
        Calculator calc = new Calculator();
        assertEquals(15, calc.calculate(10, 5, "add"));
    }
}