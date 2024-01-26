import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class NextGreaterElement {
    // file:///Users/chesin/Downloads/Telegram%20Desktop/Ace%20the%20Java%20Coding%20Interview/2.%20Data%20Structures/5.%20Stacks%20and%20Queues/18.%20Challenge%207%3A%20Next%20Greater%20Element%20using%20Stack.html
    // https://leetcode.com/problems/next-greater-element-i/submissions/453146209/
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while (t != 0) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            int[] nums1 = new int[n];
            int[] nums2 = new int[m];
            
            for (int i=0; i<n; i++) {
                nums1[i] = scanner.nextInt();
            }

            for (int i=0; i<m; i++) {
                nums2[i] = scanner.nextInt();
            }

            int[] ans = func(nums1, nums2);
            System.out.println(ans);
            t -= 1;
        }
        scanner.close();
    }


    private static int[] func(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> nextGreaterMap = new HashMap<>();

        Stack<Integer> nextGreater = new Stack<>();
        nextGreater.add(-1);

        for (int i=nums2.length-1; i>= 0; i--) {
            int n = -1;
            while (!nextGreater.empty() && nextGreater.peek() <= nums2[i]) {
                nextGreater.pop();
            }
            if (!nextGreater.empty()) {
                n = nextGreater.peek();
            }
            nextGreater.add(nums2[i]);
            nextGreaterMap.put(nums2[i], n);
        }

        int [] ans = new int[nums1.length];
        for (int i=0; i<nums1.length; i++) {
            if (nextGreaterMap.containsKey(nums1[i])) {
                ans[i] = nextGreaterMap.get(nums1[i]);
            } else {
                ans[i] = -1;
            }
        }
        return ans;
    }
}