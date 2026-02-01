package test.java.com.example;

import main.java.com.example.Calculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    public void setUp() {
        calculator = new Calculator();
    }

    @Test
    public void testAddOperation() {
        assertEquals(15, calculator.calculate(10, 5, "add"));
    }

    @Test
    public void testAddMethodDirectly() {
        assertEquals(8, calculator.add(5, 3));
        assertEquals(0, calculator.add(-5, 5));
        assertEquals(-10, calculator.add(-5, -5));
    }

    @Test
    public void testSubtractOperation() {
        assertEquals(5, calculator.calculate(10, 5, "sub"));
        assertEquals(-5, calculator.calculate(5, 10, "sub"));
        assertEquals(0, calculator.calculate(7, 7, "sub"));
    }

    @Test
    public void testMultiplyOperation() {
        assertEquals(50, calculator.calculate(10, 5, "mul"));
        assertEquals(0, calculator.calculate(10, 0, "mul"));
        assertEquals(-20, calculator.calculate(4, -5, "mul"));
    }

    @Test
    public void testDivideOperation() {
        assertEquals(2, calculator.calculate(10, 5, "div"));
        assertEquals(5, calculator.calculate(25, 5, "div"));
        assertEquals(1, calculator.calculate(7, 7, "div"));
    }

    @Test
    public void testDivideByZeroThrowsException() {
        ArithmeticException exception = assertThrows(
            ArithmeticException.class,
            () -> calculator.calculate(10, 0, "div")
        );
        assertEquals("Cannot divide by zero", exception.getMessage());
    }

    @Test
    public void testModuloOperation() {
        assertEquals(1, calculator.calculate(10, 3, "mod"));
        assertEquals(0, calculator.calculate(10, 5, "mod"));
        assertEquals(2, calculator.calculate(17, 5, "mod"));
    }

    @Test
    public void testInvalidOperation() {
        assertEquals(0, calculator.calculate(10, 5, "invalid"));
        assertEquals(0, calculator.calculate(10, 5, ""));
        assertEquals(0, calculator.calculate(10, 5, "xyz"));
    }

    @Test
    public void testAllOperationsWithZero() {
        assertEquals(0, calculator.calculate(0, 0, "add"));
        assertEquals(0, calculator.calculate(0, 0, "sub"));
        assertEquals(0, calculator.calculate(0, 0, "mul"));
        assertEquals(0, calculator.calculate(0, 5, "mod"));
    }

    @Test
    public void testNegativeNumbers() {
        assertEquals(-2, calculator.add(-5, 3));
        assertEquals(-15, calculator.calculate(-10, -5, "sub"));
        assertEquals(25, calculator.calculate(-5, -5, "mul"));
    }

    @Test
    public void testLargeNumbers() {
        assertEquals(2000, calculator.calculate(1000, 1000, "add"));
        assertEquals(1000000, calculator.calculate(1000, 1000, "mul"));
    }
}