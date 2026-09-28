/**
 * Task A2 - Student Service Records stored in a Singly Linked List.
 * Each Node holds the student data plus a "next" pointer.
 * This same class/operations must be reused in the Part D integrated system.
 */
public class StudentRecordList {

    private class Node {
        Student data;
        Node next;
        Node(Student data) { this.data = data; }
    }

    private Node head;
    private int size;

    public StudentRecordList() {
        head = null;
        size = 0;
    }

    /** Insert a new record at the start of the list. */
    public void insertAtBeginning(Student student) {
        Node newNode = new Node(student);
        newNode.next = head;
        head = newNode;
        size++;
    }

    /** Insert a new record at the end of the list. */
    public void insertAtEnd(Student student) {
        Node newNode = new Node(student);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    /** Insert a new record at a specific 1-based position (e.g. 3rd, 4th). */
    public void insertAtPosition(Student student, int position) {
        if (position <= 1 || head == null) {
            insertAtBeginning(student);
            return;
        }
        if (position > size) {
            insertAtEnd(student);
            return;
        }
        Node newNode = new Node(student);
        Node current = head;
        int count = 1;
        while (count < position - 1) {
            current = current.next;
            count++;
        }
        newNode.next = current.next;
        current.next = newNode;
        size++;
    }

    /** Required operation name from the brief - defaults to inserting at the end. */
    public void insertStudent(Student student) {
        insertAtEnd(student);
    }

    /** Deletes the record matching the given student number. Returns true if removed. */
    public boolean deleteStudent(String studentNo) {
        if (head == null) return false;

        if (head.data.getStudentNo().equals(studentNo)) {
            head = head.next;
            size--;
            return true;
        }

        Node current = head;
        while (current.next != null && !current.next.data.getStudentNo().equals(studentNo)) {
            current = current.next;
        }

        if (current.next == null) {
            return false; // not found
        }

        current.next = current.next.next; // unlink the matching node
        size--;
        return true;
    }

    /** Linear search by student number. Returns the Student, or null if not found. */
    public Student searchStudent(String studentNo) {
        Node current = head;
        while (current != null) {
            if (current.data.getStudentNo().equals(studentNo)) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    /** Traverses and prints every record in the list. */
    public void displayStudents() {
        System.out.println("---- Student Service Records ----");
        if (head == null) {
            System.out.println("(empty)");
            return;
        }
        Node current = head;
        int position = 1;
        while (current != null) {
            System.out.println(position + ". " + current.data);
            current = current.next;
            position++;
        }
        System.out.println("----------------------------------");
    }

    public int getSize() {
        return size;
    }
}
