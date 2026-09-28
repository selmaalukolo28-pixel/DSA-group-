import java.util.Random;
import java.util.Scanner;

public class ServiceCentre {

    public static int selectionSort(int[] arr) {
        int comparisons = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                comparisons++;
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            if (min != i) {
                int temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
            }
        }
        return comparisons;
    }

    public static int insertionSort(int[] arr) {
        int comparisons = 0;
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0) {
                comparisons++;
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    j--;
                } else {
                    break;
                }
            }
            arr[j + 1] = key;
        }
        return comparisons;
    }

    public static int mergeSort(int[] arr) {
        if (arr.length <= 1) {
            return 0;
        }
        int middle = arr.length / 2;
        int[] left = new int[middle];
        int[] right = new int[arr.length - middle];
        System.arraycopy(arr, 0, left, 0, middle);
        for (int i = middle; i < arr.length; i++) {
            right[i - middle] = arr[i];
        }
        int comparisons = 0;
        comparisons += mergeSort(left);
        comparisons += mergeSort(right);
        comparisons += merge(arr, left, right);
        return comparisons;
    }

    public static int merge(int[] arr, int[] left, int[] right) {
        int i = 0;
        int j = 0;
        int k = 0;
        int comparisons = 0;
        while (i < left.length && j < right.length) {
            comparisons++;
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }
        while (i < left.length) {
            arr[k] = left[i];
            i++;
            k++;
        }
        while (j < right.length) {
            arr[k] = right[j];
            j++;
            k++;
        }
        return comparisons;
    }

    public static int quickSort(int[] arr, int low, int high) {
        if (low >= high) {
            return 0;
        }
        int comparisons = 0;
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            comparisons++;
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        int position = i + 1;
        comparisons += quickSort(arr, low, position - 1);
        comparisons += quickSort(arr, position + 1, high);
        return comparisons;
    }

    public static int[] createArray(int size) {
        int[] arr = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(1000);
        }
        return arr;
    }

    public static void main(String[] args) {
        ServiceQueue waitingQueue = new ServiceQueue();
        StudentRecordList recordList = new StudentRecordList();
        DailyStatistics stats = new DailyStatistics(100);
        int choice = 0;

        try (Scanner scanner = new Scanner(System.in)) {
            while (choice != 11) {
                System.out.println("========================================");
                System.out.println("        CAMPUS SERVICE CENTRE           ");
                System.out.println("========================================");
                System.out.println("1. Add student to waiting queue");
                System.out.println("2. Serve next student (remove from queue)");
                System.out.println("3. Display waiting students");
                System.out.println("4. Add student service record (Linked List)");
                System.out.println("5. Display student service records");
                System.out.println("6. Search for student record");
                System.out.println("7. Remove student record");
                System.out.println("8. Display daily statistics");
                System.out.println("9. Sort service times");
                System.out.println("10. Run sorting experiment");
                System.out.println("11. Exit");
                System.out.print("Select option: ");

                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> {
                        System.out.println("\n--- Add Student to Waiting Queue ---");
                        System.out.print("Enter Student Number: ");
                        String no = scanner.nextLine();
                        System.out.print("Enter Student Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter Service Type: ");
                        String service = scanner.nextLine();
                        System.out.print("Enter Estimated Service Time in minutes: ");
                        int time = scanner.nextInt();
                        Student newStudent = new Student(no, name, service, time);
                        waitingQueue.enqueue(newStudent);
                    }

                    case 2 ->  {
                        System.out.println("\n--- Serve Next Student ---");
                        Student servedStudent = waitingQueue.dequeue();
                        if (servedStudent != null) {
                            stats.serviceTime(servedStudent.getEstimatedServiceTime());
                            recordList.insertStudent(servedStudent);
                            System.out.println("Record archived to Linked List and statistics.");
                        }
                    }

                    case 3 ->  {
                        System.out.println();
                        waitingQueue.displayQueue();
                    }
                    case 4 ->  {
                        System.out.println("\n--- Add Student Service Record ---");
                        System.out.print("Enter Student Number: ");
                        String no = scanner.nextLine();
                        System.out.print("Enter Student Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter Service Type: ");
                        String service = scanner.nextLine();
                        System.out.print("Enter Service Time in minutes: ");
                        int time = scanner.nextInt();
                        scanner.nextLine();

                        Student studentRecord = new Student(no, name, service, time);
                        recordList.insertStudent(studentRecord);
                        stats.serviceTime(time);
                        System.out.println("Student record was added successfully!!!");
                    }

                    case 5 ->  {
                        System.out.println();
                        recordList.displayStudents();
                    }

                    case 6 ->  {
                        System.out.println("\n--- Search Student Record ---");
                        System.out.print("Enter Student Number to search: ");
                        String searchNo = scanner.nextLine();
                        Student found = recordList.searchStudent(searchNo);
                        if (found != null) {
                            System.out.println("Found: " + found);
                        } else {
                            System.out.println("Student record not found!!!");
                        }
                    }

                    case 7 ->  {
                        System.out.println("\n--- Remove Student Record ---");
                        System.out.print("Enter Student Number to remove: ");
                        String removeNo = scanner.nextLine();
                        boolean removed = recordList.deleteStudent(removeNo);
                        if (removed) {
                            System.out.println("Record successfully deleted.");
                        } else {
                            System.out.println("Student record not found.");
                        }
                    }
                    case 8 ->  {
                        System.out.println();
                        stats.displayStatistics();
                    }

                    case 9 ->  {
                        System.out.println("\n--- Sort Service Times ---");
                        int[] sampleTimes = {17, 5, 23, 8, 14, 3, 11, 20, 6, 9};
                        int[] sampleCopy = sampleTimes.clone();
                        int comp = selectionSort(sampleCopy);
                        System.out.println("Sorted in Selection: " + comp);
                    }

                    case 10 ->  {
                        System.out.println("\n--- Run Sorting Experiment ---");
                        int[] sizes = {20, 50, 100, 500};
                        for (int size : sizes) {
                            int[] original = createArray(size);
                            
                            int[] sel = new int[original.length];
                            System.arraycopy(original, 0, sel, 0, original.length);
                            
                            int[] ins = new int[original.length];
                            System.arraycopy(original, 0, ins, 0, original.length);
                    
                            int[] mer = new int[original.length];
                            System.arraycopy(original, 0, mer, 0, original.length);
                            
                            int[] qui = new int[original.length];
                            System.arraycopy(original, 0, qui, 0, original.length);

                            long start = System.nanoTime();
                            int selComp = selectionSort(sel);
                            long selTime = System.nanoTime() - start;

                            start = System.nanoTime();
                            int insComp = insertionSort(ins);
                            long insTime = System.nanoTime() - start;

                            start = System.nanoTime();
                            int merComp = mergeSort(mer);
                            long merTime = System.nanoTime() - start;

                            start = System.nanoTime();
                            int quiComp = quickSort(qui, 0, qui.length - 1);
                            long quiTime = System.nanoTime() - start;

                            System.out.println("--- Results for Size: " + size + " ---");
System.out.println("Selection Sort -> Time: " + selTime + " ns | Comparisons: " + selComp);
System.out.println("Insertion Sort -> Time: " + insTime + " ns | Comparisons: " + insComp);
System.out.println("Merge Sort     -> Time: " + merTime + " ns | Comparisons: " + merComp);
System.out.println("Quick Sort     -> Time: " + quiTime + " ns | Comparisons: " + quiComp);
System.out.println("----------------------------------------");
                        }
                    }
                    case 11 ->  {
                        System.out.println("\nGoodbye!!");
                    }

                    default -> {
                        System.out.println("\nInvalid choice given!!!");
                    }      
                }
                System.out.println();
            }
        }
    }
}