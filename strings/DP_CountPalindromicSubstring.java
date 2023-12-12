import java.util.*;

public class DP_CountPalindromicSubstring {

    // file:///Users/chesin/Downloads/Telegram%20Desktop/Ace%20the%20Java%20Coding%20Interview/2.%20Data%20Structures/4.%20Strings/11.%20Count%20of%20Palindromic%20Substrings.html

    // https://leetcode.com/problems/palindromic-substrings/description/

    // Total number of palindromic substring

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        scanner.nextLine();

        while (t != 0) {
            String str = scanner.nextLine();
            Integer result = totalPalindromicSubstrings(str);
            System.out.println("result: " + result);
            t -= 1;
        }
        scanner.close();
    }


    /*
     * ------------- Bottom Top DP solution ---------------------------------------
     * Minor modification needed in longest palindromic substring problem
     * Count the total number of 1s present in the dp 2d array
     */


    private static Integer totalPalindromicSubstrings(String str) {
        int[][] dp = new int[str.length()][str.length()];
        // loop to compute diagonally upper half triangle
        for (int i=0; i<dp.length; i++) {
           for (int k=0, j=i; k<dp.length && j<dp[0].length; k++, j++) {
                if (str.charAt(k) == str.charAt(j)) {
                    if (k == j || j == k + 1) {
                        // base case single / double char substring
                        dp[k][j] = 1;
                    } else {
                        // check from i+1, j-1 substring
                        dp[k][j] = dp[k+1][j-1];
                    }
                } else {
                    dp[k][j] = 0;
                }
            }
        }

        Integer count = 0;
        // loop top to down and compute the total occurence of 1
        for (int i=dp.length-1; i>=0; i--) {
            for (int k=0, j=i; k<dp.length && j<dp[0].length; k++, j++) {
                if (dp[k][j] == 1) {
                    count += 1;
                }
            }
        }

        return count;
    }
}