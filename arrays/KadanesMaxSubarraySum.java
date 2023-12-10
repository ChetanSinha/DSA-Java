import java.util.ArrayList;
import java.util.List;
import java.lang.Math;
import java.util.Scanner;

public class KadanesMaxSubarraySum {

    // file:///Users/chesin/Downloads/Telegram%20Desktop/Ace%20the%20Java%20Coding%20Interview/2.%20Data%20Structures/2.%20Arrays/24.%20Solution%20Review%3A%20Find%20the%20Sum%20of%20Maximum%20Sum%20Subarray.html
    // kaden's algorithm to find max sub array sum

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();

        while (t != 0) {
            int n = scanner.nextInt();
            List<Integer> arr = new ArrayList<>(n);
            for (int i=0; i<n; i++) {
                arr.add(i, scanner.nextInt());
            }

            func(arr, n);

            for (Integer a : arr) {
                System.out.print(a + " ");
            }
            System.out.println();
            t -= 1;
        }
        scanner.close();
    }

    private static void func(List<Integer> arr, int n) {

        int currentMax = 0;
        int globalMax = 0;

        // at each index compute max sum that we can get till that index
        // include every value till the sum of values does not become negative.
        for (int i=0; i<n; i++) {
            currentMax = (currentMax < 0) ? arr.get(i) : arr.get(i) + currentMax;

            globalMax = Math.max(currentMax, globalMax);
        }

        System.out.println(globalMax);
    }
}