import java.util.Random;

public class Main {

    // ================= SELECT SORT =================
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


    // ================= INSERTION SORT =================
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


    // ================= MERGE SORT =================
    public static int mergeSort(int[] arr) {

        if (arr.length <= 1) {
            return 0;
        }

        int middle = arr.length / 2;

        int[] left = new int[middle];
        int[] right = new int[arr.length - middle];

        for (int i = 0; i < middle; i++) {
            left[i] = arr[i];
        }

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


    // ================= QUICK SORT =================
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


    // ================= CREATE RANDOM ARRAY =================
    public static int[] createArray(int size) {

        int[] arr = new int[size];

        Random random = new Random();

        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(1000);
        }

        return arr;
    }


    // ================= RUN TEST =================
    public static void test(int[] original, int size) {

        int[] selection = original.clone();
        int[] insertion = original.clone();
        int[] merge = original.clone();
        int[] quick = original.clone();


        // Selection Sort
        long start = System.nanoTime();
        int selectionComparisons = selectionSort(selection);
        long end = System.nanoTime();

        long selectionTime = end - start;


        // Insertion Sort
        start = System.nanoTime();
        int insertionComparisons = insertionSort(insertion);
        end = System.nanoTime();

        long insertionTime = end - start;


        // Merge Sort
        start = System.nanoTime();
        int mergeComparisons = mergeSort(merge);
        end = System.nanoTime();

        long mergeTime = end - start;


        // Quick Sort
        start = System.nanoTime();
        int quickComparisons = quickSort(quick, 0, quick.length - 1);
        end = System.nanoTime();

        long quickTime = end - start;


        // Display results
        System.out.println();
        System.out.println("Array Size: " + size);
        System.out.println("--------------------------------");

        System.out.println("Selection Sort");
        System.out.println("Comparisons: " + selectionComparisons);
        System.out.println("Time: " + selectionTime + " ns");

        System.out.println();

        System.out.println("Insertion Sort");
        System.out.println("Comparisons: " + insertionComparisons);
        System.out.println("Time: " + insertionTime + " ns");

        System.out.println();

        System.out.println("Merge Sort");
        System.out.println("Comparisons: " + mergeComparisons);
        System.out.println("Time: " + mergeTime + " ns");

        System.out.println();

        System.out.println("Quick Sort");
        System.out.println("Comparisons: " + quickComparisons);
        System.out.println("Time: " + quickTime + " ns");
    }


    // ================= MAIN =================
    public static void main(String[] args) {

        int[] sizes = {20, 50, 100, 500};

        for (int size : sizes) {

            int[] original = createArray(size);

            test(original, size);
        }
    }
}
