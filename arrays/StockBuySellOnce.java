import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StockBuySellOnce {
    // file:///Users/chesin/Downloads/Telegram%20Desktop/Ace%20the%20Java%20Coding%20Interview/2.%20Data%20Structures/2.%20Arrays/31.%20Stock%20Buy%20Sell%20to%20Maximize%20Profit.html
    
    // Stock buy and sell, once
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
        if (arr.size() < 2)
            return;
        int currentBuy = arr.get(0);
        int globalProfit = arr.get(1) - currentBuy;
        int currentProfit = Integer.MIN_VALUE;

        for (int i=1; i<arr.size(); i++) {
            currentProfit = arr.get(i) - currentBuy;

            if (currentProfit > globalProfit) {
                globalProfit = currentProfit;
            }

            if (arr.get(i) < currentBuy) {
                currentBuy = arr.get(i);
            }
        }

        System.out.println("Global profit: " + globalProfit);
    }
}