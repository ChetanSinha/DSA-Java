import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class QuickSort {

    // file:///Users/chesin/Downloads/Telegram%20Desktop/Ace%20the%20Java%20Coding%20Interview/2.%20Data%20Structures/2.%20Arrays/33.%20Sort%20an%20Array%20Using%20Quicksort%20Algorithm.html
    // Quick sort
    // 1. take a pivot (can be random index, first value or last value)
    // 2. find the correct position of the pivot (using partitioning by pivot technique)
    // 3. do this for the left subarray and the right subarray
    // 4. you have the sorted array now.

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();

        while (t != 0) {
            int n = scanner.nextInt();
            List<Integer> arr = new ArrayList<>(n);
            for (int i=0; i<n; i++) {
                arr.add(i, scanner.nextInt());
            }

            quickSort(arr, 0, n-1);

            for (Integer a : arr) {
                System.out.print(a + " ");
            }
            System.out.println();
            t -= 1;
        }
        scanner.close();
    }

    private static void quickSort(List<Integer> arr, int low, int high) {
        if (low < high) {
            // find partition index (the index which is at correct position)
            int paritionIdx = findPartitionIdx(arr, low, high);
            quickSort(arr, low, paritionIdx-1);
            quickSort(arr, paritionIdx+1, high);
        }
    }

    private static int findPartitionIdx(List<Integer> arr, int low, int high) {
        // first index as pivot
        int pivot = arr.get(low);
        int start = low;
        int end = high;

        while (start < end) {
            while (start < high && arr.get(start) <= pivot) {
                // find the first index from low which is greater than pivot
                start += 1;
            }

            while (end > low && arr.get(end) > pivot) {
                // find the last index from high which is lesser than pivot
                end -= 1;
            }

            if (start < end) {
                // if start and end are at valid positions
                // then we've found two indexes that need to be swapped to partition around pivot
                Collections.swap(arr, start, end);
            }
        }

        // at end, the loop get over when start and end cross each other, meaning end is now pointing at index
        // which is last smaller than the pivot, now this is the position that pivot needs to be
        // therefore swap the pivot (to place it at correct index)
        Collections.swap(arr, low, end);
        return end;
    }
}