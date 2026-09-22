
public class ServiceQueue {

  
    private class QueueNode {
        Student data;
        QueueNode next;
        QueueNode(Student data) { this.data = data; }
    }

    private QueueNode front;
    private QueueNode rear;
    private int size;

    public ServiceQueue() {
        front = null;
        rear = null;
        size = 0;
    }

    
    public void enqueue(Student student) {
        QueueNode newNode = new QueueNode(student);
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
        System.out.println("Enqueued: " + student);
    }

    
    public Student dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty. Cannot dequeue.");
            return null;
        }
        Student served = front.data;
        front = front.next;
        if (front == null) {
            rear = null; // queue became empty
        }
        size--;
        System.out.println("Dequeued (served): " + served);
        return served;
    }

    /** Looks at the student at the front without removing them. */
    public Student peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return null;
        }
        return front.data;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public int getSize() {
        return size;
    }

    
    public void displayQueue() {
        System.out.println("---- Waiting Queue (front to rear) ----");
        if (isEmpty()) {
            System.out.println("(empty)");
            return;
        }
        QueueNode current = front;
        int position = 1;
        while (current != null) {
            System.out.println(position + ". " + current.data);
            current = current.next;
            position++;
        }
        System.out.println("----------------------------------------");
    }
}
