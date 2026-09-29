import java.util.Arrays;

// ============================================================
// 1. QUEUE (Array-based Linear Queue)
// ============================================================
public class Main {
    private static final int MAX_SIZE = 100;
    private Object[] queue;
    private int front;
    private int rear;

    public Main() {
        queue = new Object[MAX_SIZE];
        front = -1;
        rear = -1;
    }
    public static void main(String[] args) {
        Main myQueue = new Main();
        
        System.out.println("Program ran successfully!");
    }
    /**
     * ALGORITHM enqueue(student)
     * Adds a student to the rear of the queue.
     */
    public void enqueue(Object student) {
        if (rear == MAX_SIZE - 1) {
            System.out.println("Queue is full");
            return;
        }
        if (front == -1) {          // queue was empty
            front = 0;
        }
        rear = rear + 1;
        queue[rear] = student;
    }

    /**
     * ALGORITHM dequeue()
     * Removes and returns the student at the front of the queue.
     * Returns null if the queue is empty.
     */
    public Object dequeue() {
        if (front == -1 || front > rear) {
            System.out.println("Queue is empty");
            return null;
        }
        Object student = queue[front];
        front = front + 1;
        if (front > rear) {         // queue became empty
            front = -1;
            rear = -1;
        }
        return student;
    }

    public boolean isEmpty() {
        return front == -1 || front > rear;
    }

    public boolean isFull() {
        return rear == MAX_SIZE - 1;
    }
}

// ============================================================
// 2. STACK (Array-based) + Postfix Evaluation
// ============================================================
class Stack {
    private static final int SIZE = 100;
    private double[] stack;
    private int top;

    public Stack() {
        stack = new double[SIZE];
        top = -1;
    }

    /**
     * ALGORITHM push(value)
     */
    public void push(double value) {
        if (top == SIZE - 1) {
            System.out.println("Stack Overflow");
            return;
        }
        top = top + 1;
        stack[top] = value;
    }

    /**
     * ALGORITHM pop()
     * Returns the top value or Double.NaN on underflow.
     */
    public double pop() {
        if (top == -1) {
            System.out.println("Stack Underflow");
            return Double.NaN;   // error indicator
        }
        double value = stack[top];
        top = top - 1;
        return value;
    }

    public boolean isEmpty() {
        return top == -1;
    }
}

class PostfixEvaluator {

    /**
     * ALGORITHM evaluatePostfix(expression)
     * expression is an array of tokens (numbers as strings or operators +, -, *, /)
     * Example: ["2", "3", "+", "4", "*"]  evaluates to 20
     */
    public static double evaluatePostfix(String[] expression) {
        if (expression == null || expression.length == 0) {
            System.out.println("Empty expression");
            return Double.NaN;
        }

        Stack stack = new Stack();

        for (String token : expression) {
            if (isNumber(token)) {
                stack.push(Double.parseDouble(token));
            } else if (isOperator(token)) {
                double operand2 = stack.pop();
                double operand1 = stack.pop();
                if (Double.isNaN(operand1) || Double.isNaN(operand2)) {
                    System.out.println("Invalid expression (stack underflow)");
                    return Double.NaN;
                }
                double result = applyOperator(operand1, operand2, token);
                stack.push(result);
            } else {
                System.out.println("Invalid token: " + token);
                return Double.NaN;
            }
        }

        double result = stack.pop();
        if (!stack.isEmpty()) {
            System.out.println("Invalid expression (leftover values on stack)");
            return Double.NaN;
        }
        return result;
    }

    private static boolean isNumber(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") ||
               token.equals("*") || token.equals("/") ||
               token.equals("x");
    }

    private static double applyOperator(double op1, double op2, String operator) {
        switch (operator) {
            case "+":
                return op1 + op2;
            case "-":
                return op1 - op2;
            case "*":
            case "x":
                return op1 * op2;
            case "/":
                if (op2 == 0) {
                    System.out.println("Division by zero");
                    return Double.NaN;
                }
                return op1 / op2;
            default:
                throw new IllegalArgumentException("Unknown operator: " + operator);
        }
    }
}

// ============================================================
// 3. SINGLY LINKED LIST
// ============================================================
class Student {
    String studentNo;
    String name;

    public Student(String studentNo, String name) {
        this.studentNo = studentNo;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{studentNo='" + studentNo + "', name='" + name + "'}";
    }
}

class Node {
    Student data;
    Node next;

    public Node(Student data) {
        this.data = data;
        this.next = null;
    }
}

class SinglyLinkedList {
    private Node head;

    public SinglyLinkedList() {
        head = null;
    }

    /**
     * ALGORITHM insertNode(student, position)
     * Inserts a new node containing student at the given 1-based position.
     * If position == 1 or list is empty, inserts at the beginning.
     */
    public void insertNode(Student student, int position) {
        Node newNode = new Node(student);

        if (position <= 1 || head == null) {   // insert at beginning
            newNode.next = head;
            head = newNode;
            return;
        }

        Node current = head;
        int count = 1;
        while (current.next != null && count < position - 1) {
            current = current.next;
            count = count + 1;
        }

        // If position is beyond the end, append at the end instead of dropping.
        newNode.next = current.next;
        current.next = newNode;
    }

    /**
     * ALGORITHM deleteNode(studentNumber)
     * Deletes the first node whose studentNo matches studentNumber.
     * Returns true if deleted, false otherwise.
     */
    public boolean deleteNode(String studentNumber) {
        if (head == null) {
            return false;
        }

        if (head.data.studentNo.equals(studentNumber)) {
            head = head.next;
            return true;
        }

        Node current = head;
        while (current.next != null) {
            if (current.next.data.studentNo.equals(studentNumber)) {
                current.next = current.next.next;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * ALGORITHM searchNode(studentNumber)
     * Returns the Student object if found, otherwise null.
     */
    public Student searchNode(String studentNumber) {
        Node current = head;
        while (current != null) {
            if (current.data.studentNo.equals(studentNumber)) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    /**
     * ALGORITHM traverseList()
     * Prints all student data in the list.
     */
    public void traverseList() {
        Node current = head;
        if (current == null) {
            System.out.println("List is empty");
            return;
        }
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }
}

// ============================================================
// 4-7. SORTING ALGORITHMS
// ============================================================
class SortingAlgorithms {

    /**
     * ALGORITHM selectionSort(A, n)
     */
    public static void selectionSort(int[] A, int n) {
        for (int i = 0; i <= n - 2; i++) {
            int minIndex = i;
            for (int j = i + 1; j <= n - 1; j++) {
                if (A[j] < A[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = A[i];
                A[i] = A[minIndex];
                A[minIndex] = temp;
            }
        }
    }

    /**
     * ALGORITHM insertionSort(A, n)
     */
    public static void insertionSort(int[] A, int n) {
        for (int i = 1; i <= n - 1; i++) {
            int key = A[i];
            int j = i - 1;
            while (j >= 0 && A[j] > key) {
                A[j + 1] = A[j];    // shift
                j = j - 1;
            }
            A[j + 1] = key;
        }
    }

    /**
     * ALGORITHM mergeSort(A, left, right)
     */
    public static void mergeSort(int[] A, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;   // avoids overflow
            mergeSort(A, left, mid);
            mergeSort(A, mid + 1, right);
            merge(A, left, mid, right);
        }
    }

    /**
     * ALGORITHM merge(A, left, mid, right)
     */
    private static void merge(int[] A, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        for (int i = 0; i < n1; i++) {
            leftArray[i] = A[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArray[j] = A[mid + 1 + j];
        }

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                A[k] = leftArray[i];
                i = i + 1;
            } else {
                A[k] = rightArray[j];
                j = j + 1;
            }
            k = k + 1;
        }

        while (i < n1) {
            A[k] = leftArray[i];
            i = i + 1;
            k = k + 1;
        }
        while (j < n2) {
            A[k] = rightArray[j];
            j = j + 1;
            k = k + 1;
        }
    }

    /**
     * ALGORITHM quickSort(A, low, high)
     */
    public static void quickSort(int[] A, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(A, low, high);
            quickSort(A, low, pivotIndex - 1);
            quickSort(A, pivotIndex + 1, high);
        }
    }

    /**
     * ALGORITHM partition(A, low, high)
     * Pivot-selection rule: last element
     */
    private static int partition(int[] A, int low, int high) {
        int pivot = A[high];
        int i = low - 1;
        for (int j = low; j <= high - 1; j++) {
            if (A[j] <= pivot) {
                i = i + 1;
                int temp = A[i];
                A[i] = A[j];
                A[j] = temp;
            }
        }
        int temp = A[i + 1];
        A[i + 1] = A[high];
        A[high] = temp;
        return i + 1;
    }
}

