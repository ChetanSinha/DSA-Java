import java.util.*;

public class DP_InsertionDeletionForPalindrome {

    // file:///Users/chesin/Downloads/Telegram%20Desktop/Ace%20the%20Java%20Coding%20Interview/2.%20Data%20Structures/4.%20Strings/12.%20Minimum%20Deletions%20in%20a%20String%20to%20make%20it%20a%20Palindrome.html
    // https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/submissions/

    // Min insertion/deletion required to make string palindrome

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        scanner.nextLine();

        while (t != 0) {
            String str = scanner.nextLine();
            Integer result = totalInsertionsOrDeletionToMakePalindrome(str);
            System.out.println("result: " + result);
            t -= 1;
        }
        scanner.close();
    }


    /*
     * ------------- Bottom Top DP solution ---------------------------------------
     * Longest palindromic subsequence gives maximum length of palindrome that we can get between i and j
     * Therefore, total string length - longest palindromic subsequence will give insertion/deletions required
     */

    private static Integer totalInsertionsOrDeletionToMakePalindrome(String str) {
        return str.length() - longestSubsequence(str);
    }


    private static Integer longestSubsequence(String str) {
        int[][] dp = new int[str.length()][str.length()];
        // loop to compute diagonally upper half triangle
        for (int i=0; i<dp.length; i++) {
           for (int k=0, j=i; k<dp.length && j<dp[0].length; k++, j++) {
                if (k == j) {
                    dp[k][j] = 1;
                } else if (j == k + 1) {
                    if (str.charAt(j) == str.charAt(k)) {
                        dp[k][j] = 2;
                    } else {
                        dp[k][j] = 1;
                    }
                } else {
                    if (str.charAt(j) == str.charAt(k)) {
                        dp[k][j] = Math.max(dp[k+1][j-1] + 2, Math.max(dp[k+1][j], dp[k][j-1]));
                    } else {
                        dp[k][j] = Math.max(dp[k+1][j-1], Math.max(dp[k+1][j], dp[k][j-1]));
                    }
                }
            }
        }

        return dp[0][str.length()-1];
    }
}