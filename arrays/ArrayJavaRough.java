import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArrayJavaRough {

    // https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/

    // file:///Users/chesin/Downloads/Telegram%20Desktop/Ace%20the%20Java%20Coding%20Interview/2.%20Data%20Structures/2.%20Arrays/51.%20Next%20Letter%20(medium).html

    // First and last occurence of key
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();

        while (t != 0) {
            int n = scanner.nextInt();
            List<Integer> arr = new ArrayList<>(n);
            for (int i=0; i<n; i++) {
                arr.add(i, scanner.nextInt());
            }

            int key = scanner.nextInt();

            func(arr, n, key);

            for (Integer a : arr) {
                System.out.print(a + " ");
            }
            System.out.println();
            t -= 1;
        }
        scanner.close();
    }

    private static void func(List<Integer> arr, int n, int key) {
        int first = findCeil(arr, key);
        int last = findFloor(arr, key);

        System.out.println("First idx: " + first + " , Last idx: " + last);
    }

    private static int findCeil(List<Integer> arr, int key) {

        // binary search based method to find ceil
        int start = 0;
        int end = arr.size() - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (arr.get(mid) < key) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        // after finding ceil, if the before element is key then return the position
        if (end + 1 < arr.size() && arr.get(end + 1) == key) {
            return end + 1;
        }
        return -1;
    }

    private static int findFloor(List<Integer> arr, int key) {
        // binary search based method to find floor
        int start = 0;
        int end = arr.size() - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (arr.get(mid) > key) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        // after finding floor, if next element is key then return the position
        if (start - 1 >= 0 && arr.get(start - 1) == key) {
            return start - 1;
        }
        return -1;
    }
}