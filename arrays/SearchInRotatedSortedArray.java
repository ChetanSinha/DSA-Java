import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SearchInRotatedSortedArray {

    // file:///Users/chesin/Downloads/Telegram%20Desktop/Ace%20the%20Java%20Coding%20Interview/2.%20Data%20Structures/2.%20Arrays/27.%20Search%20a%20Rotated%20Array.html

    // search in ratoted sorted array.
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();

        while (t != 0) {
            int n = scanner.nextInt();
            List<Integer> arr = new ArrayList<>(n);
            for (int i=0; i<n; i++) {
                arr.add(i, scanner.nextInt());
            }
            int num = scanner.nextInt();

            func(arr, n, num);

            for (Integer a : arr) {
                System.out.print(a + " ");
            }
            System.out.println();
            t -= 1;
        }
        scanner.close();
    }

    private static void func(List<Integer> arr, int n, int num) {
        // the rotation can be on any one index of the array.
        // since the rotation is about one index, always one half will be sorted.

        int left = 0;
        int right = n-1;

        while (left <= right) {
            int mid = left + (right-left)/2;

            if (arr.get(mid) == num) {
                System.out.println("Found num at: " + mid);
                return;
            }
            if (arr.get(mid) >= arr.get(left)) {
                // either left part is sorted
                if (arr.get(left) <= num && arr.get(mid) > num) {
                    // search in sorted left part (typical binary search)
                    right = mid - 1;
                } else {
                    // search in right part (unsorted part), smaller subspace
                    left = mid + 1;
                }
            } else {
                // or right part is sorted
                if (arr.get(mid) < num && arr.get(right) >= num) {
                    // search in sorted right part (typical binary search)
                    left = mid + 1;
                } else {
                    // search in left part (unsorted part), smaller subspace
                    right = mid - 1;
                }
            }
        }

        System.out.println("Not found");
    }
}