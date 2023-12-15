import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JavaRough {
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

    }
}