public class PartADemo {
    public static void main(String[] args) {

        System.out.println("=========================================");
        System.out.println(" TASK A1 - QUEUE DEMONSTRATION");
        System.out.println("=========================================");
        ServiceQueue queue = new ServiceQueue();
        queue.enqueue(new Student("221045678", "Maria", "Registration", 12));
        queue.enqueue(new Student("222034512", "Tomas", "Student Card", 5));
        queue.enqueue(new Student("223041876", "Ndapewa", "Fees", 8));
        queue.enqueue(new Student("221067341", "Simon", "Documents", 4));
        queue.enqueue(new Student("223055012", "Helena", "Academic Enquiry", 10));
        queue.enqueue(new Student("221098234", "Petrus", "Fees", 6));
        queue.displayQueue();

        System.out.println("\nServing 3 students...");
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.displayQueue();

        System.out.println("\n=========================================");
        System.out.println(" TASK A2 - SINGLY LINKED LIST DEMONSTRATION");
        System.out.println("=========================================");
        StudentRecordList list = new StudentRecordList();
        list.insertAtEnd(new Student("221045678", "Maria", "Registration", 12));
        list.insertAtEnd(new Student("222034512", "Tomas", "Student Card", 5));
        list.insertAtEnd(new Student("223041876", "Ndapewa", "Fees", 8));
        System.out.println("After 3 insertions at the end:");
        list.displayStudents();

        list.insertAtBeginning(new Student("221067341", "Simon", "Documents", 4));
        System.out.println("\nAfter inserting Simon at the beginning:");
        list.displayStudents();

        list.insertAtPosition(new Student("223055012", "Helena", "Academic Enquiry", 10), 3);
        System.out.println("\nAfter inserting Helena at position 3:");
        list.displayStudents();

        System.out.println("\nSearching for student 223041876 (Ndapewa):");
        Student found = list.searchStudent("223041876");
        System.out.println(found != null ? "Found: " + found : "Not found.");

        System.out.println("\nDeleting student 222034512 (Tomas):");
        list.deleteStudent("222034512");
        list.displayStudents();

        System.out.println("\n=========================================");
        System.out.println(" TASK A3 - POSTFIX EXPRESSION EVALUATION");
        System.out.println("=========================================");
        PostfixEvaluator evaluator = new PostfixEvaluator(50);
        String expression = "5 3 + 2 *";
        System.out.println("Expression: " + expression);
        evaluator.evaluate(expression);

        System.out.println("\n=========================================");
        System.out.println(" TASK A4 - DAILY STATISTICS (ARRAY)");
        System.out.println("=========================================");
        DailyStatistics stats = new DailyStatistics(50);
        int[] todaysServiceTimes = {12, 5, 8, 4, 10, 6, 15, 3, 20, 7};
        for (int time : todaysServiceTimes) {
            stats.addServiceTime(time);
        }
        stats.displayStatistics();
    }
}
