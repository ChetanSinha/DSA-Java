import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ThreeSumZero {

    // sum of triplets to zero
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
        arr.sort(null);
        for (int i=0; i<arr.size(); i++) {
            if (i > 0 && arr.get(i) == arr.get(i-1)) {
                continue;
            }
        // for each number, find two sum (as X+Y+Z = 0, then Y+Z=-X)
            searchPairs(arr, -arr.get(i), i+1);
        }
    }

    private static void searchPairs(List<Integer> arr, int num, int left) {
        int right = arr.size()-1;

        while (left < right) {
            if ((arr.get(left) + arr.get(right)) == num) {
                System.out.println("found triplets: " + -num + ", " + arr.get(left) + ", " + arr.get(right));
                left += 1;
                right -= 1;
            } else if ((arr.get(left) + arr.get(right)) > num) {
                right -= 1;
            } else {
                left += 1;
            }
        }
    }
}