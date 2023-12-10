import java.util.*;

public class StringJavaRough {

    // file:///Users/chesin/Downloads/Telegram%20Desktop/Ace%20the%20Java%20Coding%20Interview/2.%20Data%20Structures/4.%20Strings/10.%20Longest%20Palindromic%20Subsequence.html
    // https://leetcode.com/problems/longest-palindromic-subsequence/submissions/


    // Longest palindromic subsequence

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        scanner.nextLine();

        while (t != 0) {
            String str = scanner.nextLine();
            Integer res = func2(str);
            System.out.println("result: " + res);
            t -= 1;
        }
        scanner.close();
    }


    /*
     * ---------------------- Recursive solution with memoized -------------------
     * same as that of substrings problem
     * here in each substring from i to j, find longest palindromic subsequence that we can get from it
     * same three cases to check for
     * 1. compute on (i+1, j-1)
     * 2. compute on (i, j-1)
     * 3. compute on (i+1, j)
     * 
     * current end chars of substring (ie i, j) will be part of palindrome for only i+1, j-1 case.
     * as other two cases include i or j in them.
     */

    private static Integer func1(String str) {
        int[][] dp = new int[str.length()][str.length()];
        for (int i=0; i<str.length(); i++) {
            for (int j=0; j<str.length(); j++) {
                dp[i][j] = -1;
            }
        }
        return longestPalindrome(str, 0, str.length()-1, dp);
    }

    private static Integer longestPalindrome(String str, int sIdx, int eIdx, int[][] dp) {
        if (dp[sIdx][eIdx] != -1) {
            return dp[sIdx][eIdx];
        }
        if (sIdx >= eIdx) { // base case for length 1 string
            return dp[sIdx][eIdx] = 1;
        }

        if (eIdx == sIdx + 1) { // base case for length 2 string
            if (str.charAt(eIdx) == str.charAt(sIdx)) {
                return dp[sIdx][eIdx] = 2;
            }
            return dp[sIdx][eIdx] = 1;
        }

        Integer twoLessPalindrome = longestPalindrome(str, sIdx+1, eIdx-1, dp);
        Integer oneLeftLessPalindrome = longestPalindrome(str, sIdx+1, eIdx, dp);
        Integer oneRightLessPalindrome = longestPalindrome(str, sIdx, eIdx-1, dp);

        if (str.charAt(eIdx) == str.charAt(sIdx)) {
            // add current endpoints (if equal) only for i+1, j-1 case
            twoLessPalindrome += 2;
        }

        // answer will be the max of all the longest subsequences recieved from each subspace.
        Integer ans = Math.max(twoLessPalindrome, Math.max(oneLeftLessPalindrome, oneRightLessPalindrome));
        return dp[sIdx][eIdx] = ans;
    }


    /*
     * ------------- Bottom Top DP solution ---------------------------------------
     * same as longest palindromic substring
     * here we just change the cases accordingly (explained in recursive step.)
     */


    private static Integer func2(String str) {
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