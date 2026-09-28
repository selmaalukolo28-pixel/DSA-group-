public class InsertionSort {
    public static void insertionSort(int[] arr) {
        int comparisons = 0;
        int shifts = 0;
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0) {
                comparisons++;
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    shifts++;
                    j--;
                } else {
                    break;
                }
            }
            arr[j + 1] = key;
        }
        System.out.println("Insertion Sort -> Comparisons: " + comparisons + ", Shifts: " + shifts);
    }
}