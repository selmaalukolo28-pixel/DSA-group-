// Performs selection sort in ascending order
// public class SelectionSort {

    public static void main(String[] args) {

        int[] numbers = {17, 5, 23, 8, 14, 3, 11, 20, 6, 9};

        int comparisons = 0;
        int swaps = 0;

        System.out.println("Original Array:");
        displayArray(numbers);

        // Selection Sort
        for (int i = 0; i < numbers.length - 1; i++) {

            int smallest = i;

            for (int j = i + 1; j < numbers.length; j++) {
                comparisons++;

                if (numbers[j] < numbers[smallest]) {
                    smallest = j;
                }
            }

            if (smallest != i) {
                int temp = numbers[i];
                numbers[i] = numbers[smallest];
                numbers[smallest] = temp;

                swaps++;
            }

            // Show first 3 passes
            if (i < 3) {
                System.out.print("After Pass " + (i + 1) + ": ");
                displayArray(numbers);
            }
        }

        System.out.println("\nSorted Array:");
        displayArray(numbers);

        System.out.println("Comparisons: " + comparisons);
        System.out.println("Swaps: " + swaps);
    }

    public static void displayArray(int[] numbers) {
        for (int number : numbers) {
            System.out.print(number + " ");
        }
        System.out.println();
    }