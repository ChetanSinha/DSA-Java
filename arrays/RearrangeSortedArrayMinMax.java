import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RearrangeSortedArrayMinMax {

    // file:///Users/chesin/Downloads/Telegram%20Desktop/Ace%20the%20Java%20Coding%20Interview/2.%20Data%20Structures/2.%20Arrays/22.%20Solution%20Review%3A%20Re-arrange%20Sorted%20Array%20in%20Max-Min%20Form.html

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

        int baseMax = arr.get(n-1)+1;

        int start = 0;
        int end = n-1;


        // enhanced two pointers without space complexity
        for (int i=0; i<n; i++) {

            // store both required number and current number both at the index
            int requiredNum = (i%2 == 0) ? arr.get(end--) : arr.get(start++);
            int currentNum = arr.get(i);

            // currentNum is needed to be stored to perform basic two pointer selection for the requiredNum.
            int storeBoth = currentNum + ((requiredNum % baseMax) * baseMax);
            arr.set(i, storeBoth);
        }

        for (int i=0; i<n; i++) {
            int requiredNum = arr.get(i) / baseMax;
            arr.set(i, requiredNum);
        }
    }
}