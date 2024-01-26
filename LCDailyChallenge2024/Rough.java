package LCDailyChallenge2024;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Rough {

        static int counter;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();

        while (t != 0) {
            int n = scanner.nextInt();
            int[] arr = new int[n];
            for (int i=0; i<n; i++) {
                arr[i] = scanner.nextInt();
            }

            int ans = minimumCost(arr);
            System.out.println(ans);
            t -= 1;
        }
        scanner.close();
    }

    public static int minimumCost(int[] nums) {
        int ans = nums[0];
        ans += func(nums, 0, 1);
        return ans;
    }

    private static int func(int[] nums, int taken, int idx) {
        if (idx >= nums.length) {
            return 10000;
        }


        if (taken == 1) {
            return nums[idx];
        }

        int tk = func(nums, taken+1, idx+1) + nums[idx];
        int ntk = func(nums, taken, idx+1);

        int res = Math.min(tk, ntk);
        return res;
    }
}