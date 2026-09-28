
public class PostfixEvaluator {
    private double[] stackArray;
    private int top;
    private int capacity;

    public PostfixEvaluator(int capacity) {
        this.capacity = capacity;
        stackArray = new double[capacity];
        top = -1; // empty stack
    }

    public void push(double value) {
        if (top == capacity - 1) {
            System.out.println("Stack overflow.");
            return;
        }
        stackArray[++top] = value;
    }

    public double pop() {
        if (isEmpty()) {
            System.out.println("Stack underflow.");
            return 0;
        }
        return stackArray[top--];
    }

    public double peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
            return 0;
        }
        return stackArray[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    private void displayStack() {
        System.out.print("   Stack (bottom -> top): [");
        for (int i = 0; i <= top; i++) {
            System.out.print(stackArray[i]);
            if (i < top) System.out.print(", ");
        }
        System.out.println("]");
    }

    /**
     * Evaluates a postfix expression given as space-separated tokens,
     * e.g. "5 3 + 2 *". Supports +, -, * (or x), / (or ÷).
     */
    public double evaluate(String postfixExpression) {
        String[] tokens = postfixExpression.trim().split("\\s+");
        for (String token : tokens) {
            if (isOperator(token)) {
                double operand2 = pop();
                double operand1 = pop();
                double result = applyOperator(token, operand1, operand2);
                push(result);
                System.out.println("Applied: " + operand1 + " " + token + " " + operand2 + " = " + result);
            } else {
                double value = Double.parseDouble(token);
                push(value);
                System.out.println("Pushed: " + value);
            }
            displayStack();
        }
        double finalResult = pop();
        System.out.println("Final Result: " + finalResult);
        return finalResult;
    }

    private boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*")
                || token.equals("/") || token.equals("x") || token.equals("÷");
    }

    private double applyOperator(String operator, double a, double b) {
        switch (operator) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": case "x": return a * b;
            case "/": case "÷":
                if (b == 0) {
                    System.out.println("Division by zero error.");
                    return 0;
                }
                return a / b;
            default:
                throw new IllegalArgumentException("Unknown operator: " + operator);
        }
    }
}
