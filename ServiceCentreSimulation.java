import java.util.Scanner;

public class ServiceCentreSimulation {

    private static int size;

    public static void selectionSort(int[] arr, int size) {
    int comparisons = 0;
    for (int i = 0; i < size - 1; i++) {
        int min = i;
        for (int j = i + 1; j < size; j++) {
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

    System.out.println("Selection Sort Comparisons: " + comparisons);
}

    public static void insertionSort(int[] arr, int size) {
        int comparisons = 0;
        for (int i = 1; i < size; i++) {
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
        System.out.println("Insertion Sort comparisons: " + comparisons);
    }

    public static void mergeSort(int[] arr, int size) {
        if (size <= 1) {
            return;
        }
        int middle = size / 2;
        int[] left = new int[middle];
        int[] right = new int[size - middle];

        for (int i = middle; i < size; i++) {
            right[i - middle] = arr[i];
        }

    mergeSort(left, middle);
   mergeSort(right, size - middle);
   merge(arr, left, right);
    }

    public static void merge(int[] arr, int[] left, int[] right) {
        int i = 0;
        int j = 0;
        int k = 0;
        int comparisons = 0;
        int leftSize = left.length; 
        int rightSize = right.length;

        while (i < leftSize && j < rightSize) {
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
        while (i < leftSize) {
            arr[k] = left[i];
            i++;
            k++;
        }
        while (j < rightSize) {
            arr[k] = right[j];
            j++;
            k++;
        }
        System.out.println("Insertion Sort comparisons: " + comparisons);
    }


    public static void quickSort(int[] arr, int low, int high) {
        if (low >= high) {
            int pivot = arr[high];
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
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

         quickSort(arr, low, position - 1);
         quickSort(arr, position + 1, high);

    }
}

    public static int[] createArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = ( i * 17) % 1000;
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
                    case 1: {
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

                    case 2:{
                        System.out.println("\n--- Serve Next Student ---");
                        Student servedStudent = waitingQueue.dequeue();
                        if (servedStudent != null) {
                            stats.serviceTime(servedStudent.getEstimatedServiceTime());
                            recordList.insertStudent(servedStudent);
                            System.out.println("Record archived to Linked List and statistics.");
                        }
                    }

                    case 3: {
                        System.out.println();
                        waitingQueue.displayQueue();
                    }
                    case 4: {
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

                    case 5: {
                        System.out.println();
                        recordList.displayStudents();
                    }

                    case 6:{
    System.out.println("\n--- Search Student Record ---");
    System.out.print("Enter Student Number to search: ");
    
    Student found = recordList.searchStudent(scanner.nextLine());
    if (found != null) {
        System.out.println("Found: " + found);
    } else {
        System.out.println("Student record not found!!!");
    }
    break;
}

                    case 7: {
                        System.out.print("\nEnter Student Number to remove: ");
    
    if (recordList.deleteStudent(scanner.nextLine())) {
        System.out.println("Record successfully deleted.");
    } else {
        System.out.println("Student record not found.");
    }
    break;
                    }
                    case 8:{
                        System.out.println();
                        stats.displayStatistics();
                    }

                    case 9: {
                        System.out.println("\n--- Sort Service Times ---");
                        int[] sampleTime = {17, 5, 23, 8, 14, 3, 11, 20, 6, 9};
                        size = 10;

                        int[] sel = new int[size];
                        for ( int i = 0; i < size; i++) {
                            sel[i] = sampleTime[i];
                        }
                        selectionSort(sel, size);
                        System.out.println("Sorted in Selection: " );
                    }

                    case 10: {
                        System.out.println("\n--- Run Sorting Experiment ---");
                        int[] sizes = {20, 50, 100, 500};
                        for (final int size : sizes) {
                            int[] original = createArray(size);
                            
                           int[] sel = new int[original.length];
                            
                            selectionSort(sel, size); // Just run it plainly!
                            
                            int[] ins = new int[original.length];
                            insertionSort(ins, size );
                    
                            int[] mer = new int[original.length];
                            mergeSort(mer, size);
                            
                            int[] qui = new int[original.length];
                            quickSort(qui, 0, size - 1);


                            System.out.println("--- Results for Size: " + size + " ---");
System.out.println("Selection Sort : Completed successfully!!");
System.out.println("Insertion Sort : Completed successfully!!");
System.out.println("Merge Sort : Completed successfully!!");
System.out.println("Quick Sort : Completed successfully!!");
System.out.println("----------------------------------------");
                        }
                    }
                    case 11: {
                        System.out.println("\nGoodbye!!");
                    }

                    default: {
                        System.out.println("\nInvalid choice given!!!!");
                    }      
                }
                System.out.println();
            }
        }
    }
}