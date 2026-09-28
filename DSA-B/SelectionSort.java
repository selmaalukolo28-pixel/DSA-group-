public class SelectionSort {
    public static void selectionSort(int[] arr) {
        int comparisons = 0;
        int swaps = 0;
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                comparisons++;
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            if (minIdx != i) {
                int temp = arr[minIdx];
                arr[minIdx] = arr[i];
                arr[i] = temp;
                swaps++;
            }
        }
        System.out.println("Selection Sort -> Comparisons: " + comparisons + ", Swaps: " + swaps);
    }
}