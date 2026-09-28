public class MergeSort {
    private static int comparisons = 0;

    public static void mergeSort(int[] arr, int left, int right) {
        if (left == 0 && right == arr.length - 1) {
            comparisons = 0; // Reset counter at the start of a new sort
        }
        if (left < right) {
            int mid = left + (right - left) / 2;

            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            merge(arr, left, mid, right);
        }
        if (left == 0 && right == arr.length - 1) {
            System.out.println("Merge Sort -> Comparisons: " + comparisons);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++) L[i] = arr[left + i];
        for (int j = 0; j < n2; j++) R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            comparisons++;
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) { arr[k++] = L[i++]; }
        while (j < n2) { arr[k++] = R[j++]; }
    }
}