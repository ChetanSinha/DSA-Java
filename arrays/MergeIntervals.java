import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.lang.Math;

public class MergeIntervals {
    // file:///Users/chesin/Downloads/Telegram%20Desktop/Ace%20the%20Java%20Coding%20Interview/2.%20Data%20Structures/2.%20Arrays/48.%20Merge%20Intervals%20(medium).html
    
    // Merge intervals
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();

        while (t != 0) {
            int n = scanner.nextInt();
            List<List<Integer>> arr = new ArrayList<>(n);
            for (int i=0; i<n; i++) {
                List<Integer> row = new ArrayList<>(2);
                for (int j=0; j<2; j++) {
                    row.add(scanner.nextInt());
                }
                arr.add(i, row);
            }

            func(arr, n);
            t -= 1;
        }
        scanner.close();
    }

    private static void func(List<List<Integer>> arr, int n) {

        if (arr.size() < 2) {
            return;
        }

        // sort by start
        Collections.sort(arr, (a, b) -> Integer.compare(a.get(0), b.get(0)));

        int low = arr.get(0).get(0);
        int high = arr.get(0).get(1);
        int ptr = 1;

        // merge

        while (ptr < n) {

            if (arr.get(ptr).get(0) <= high) {
                high = Math.max(high, arr.get(ptr).get(1));
            } else {
                System.out.println("low: " + low + " : high: " + high);
                low = arr.get(ptr).get(0);
                high = arr.get(ptr).get(1);
            }
            ptr += 1;
        }
        System.out.println("low: " + low + " : high: " + high);
    }
}