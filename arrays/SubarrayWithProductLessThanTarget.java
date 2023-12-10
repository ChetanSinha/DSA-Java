import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SubarrayWithProductLessThanTarget {

    // file:///Users/chesin/Downloads/Telegram%20Desktop/Ace%20the%20Java%20Coding%20Interview/2.%20Data%20Structures/2.%20Arrays/47.%20Subarrays%20with%20Product%20Less%20than%20a%20Target%20(medium).html
    
    // find all subsets whose product is less than target.
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();

        while (t != 0) {
            int n = scanner.nextInt();
            List<Integer> arr = new ArrayList<>(n);
            for (int i=0; i<n; i++) {
                arr.add(i, scanner.nextInt());
            }

            int target = scanner.nextInt();

            func(arr, n, target);

            for (Integer a : arr) {
                System.out.print(a + " ");
            }
            System.out.println();
            t -= 1;
        }
        scanner.close();
    }

    private static void func(List<Integer> arr, int n, int target) {
        
        int left = 0;
        int right = 0;
        int product = 1;

        // sliding window type
        while (right < n) {
            // include every item in the product
            product *= arr.get(right);

            // till the product gets more than target
            while (product >= target) {
                // remove from start till product does not get less than target
                product /= arr.get(left);
                left += 1;
            }

            // to print each subset, move backward from end to start of window
            List<Integer> list = new ArrayList<>();
            for (int i = right; i >= left; i--) {
                list.add(arr.get(i));
                System.out.println(list);
            }

            right += 1;
        }
    }
}