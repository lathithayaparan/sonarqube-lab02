package main.java.com.example;

public class Calculator {

    public int calculate(int a, int b, String op) {
        switch (op) {
            case "add":
                return add(a, b);
            case "sub":
                return a - b;
            case "mul":
                return a * b;
            case "div":
                return divide(a, b);
            case "mod":
                return a % b;
            default:
                return 0;
        }
    }

    public int add(int a, int b) {
        return a + b;
    }

    private int divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return a / b;
    }
}