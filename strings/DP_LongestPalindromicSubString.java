import java.util.*;

public class DP_LongestPalindromicSubString {

    // file:///Users/chesin/Downloads/Telegram%20Desktop/Ace%20the%20Java%20Coding%20Interview/2.%20Data%20Structures/4.%20Strings/9.%20Longest%20Palindromic%20Substring.html
    // https://leetcode.com/problems/longest-palindromic-substring/submissions/


    // Longest palindromic substring

    static Integer MAX_SUBSTRING_LENGTH = Integer.MIN_VALUE;
    static String LONGEST_SUBSTRING = "";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        scanner.nextLine();

        while (t != 0) {
            String str = scanner.nextLine();
            String result = func2(str);
            System.out.println("result: " + result);
            t -= 1;
        }
        scanner.close();
    }

    /* ------------------- Naive approach with memoization -----------------------------
     * for each substring (total n(n+1)/2) check if the substring is palindrome
     * while checking palindrome there will be overlapping substrings which can be cached
     */

    private static String func(String str) {
        String longestSubstring = "";
        int[][] dp = new int[str.length()][str.length()];
        for (int i=0; i<str.length(); i++) {
            for (int j=0; j<str.length(); j++) {
                dp[i][j] = -1;
            }
        }

        for (int i=0; i<str.length(); i++) {
            for (int j=i; j<str.length(); j++) {
                if (checkPalindrome(str, i, j, dp)) {
                    if (MAX_SUBSTRING_LENGTH < j-i+1) {
                        MAX_SUBSTRING_LENGTH = j-i+1;
                        longestSubstring = str.substring(i, j+1);
                    }                    
                }
            }
        }
        return longestSubstring;
    }

    private static boolean checkPalindrome(String str, Integer sIdx, Integer eIdx, int[][] dp) {
        if (sIdx >= eIdx) {
            return true;
        }
        if (dp[sIdx][eIdx] != -1) {
            return dp[sIdx][eIdx] == 1;
        }

        boolean ans = false;
        if (str.charAt(sIdx) == str.charAt(eIdx)) {
            ans = checkPalindrome(str, sIdx+1, eIdx-1, dp);
        }
        dp[sIdx][eIdx] = (ans) ? 1 : 0;
        return ans;
    }


    /*
     * ---------------------- Recursive solution with memoized -------------------
     * to check all substring between two indexes (i, j) three cases possible:
     * 1. compute on (i+1, j-1)
     * 2. compute on (i, j-1)
     * 3. compute on (i+1, j)
     * 
     * current substring (between i, j) will be palindrome only when:
     *      1. char at i and j are same
     *      2. case 1 is also a palindrome
     */

    private static String func1(String str) {
        int[][] dp = new int[str.length()][str.length()];
        for (int i=0; i<str.length(); i++) {
            for (int j=0; j<str.length(); j++) {
                dp[i][j] = -1;
            }
        }
        longestPalindrome(str, 0, str.length()-1, dp);
        return LONGEST_SUBSTRING;
    }

    private static boolean longestPalindrome(String str, int sIdx, int eIdx, int[][] dp) {
        if (sIdx >= eIdx) {
            if (MAX_SUBSTRING_LENGTH < eIdx - sIdx + 1) {
                MAX_SUBSTRING_LENGTH = eIdx - sIdx + 1;
                LONGEST_SUBSTRING = str.substring(sIdx, eIdx+1);
            }
            return true;
        }
        if (dp[sIdx][eIdx] != -1) {
            return dp[sIdx][eIdx] == 1;
        }
        boolean isTwoLessPalindrome = longestPalindrome(str, sIdx+1, eIdx-1, dp); // case 1
        longestPalindrome(str, sIdx, eIdx-1, dp); // case 2
        longestPalindrome(str, sIdx+1, eIdx, dp); // case 3
        boolean res = false;
        if (str.charAt(eIdx) == str.charAt(sIdx) && isTwoLessPalindrome) {
            if (MAX_SUBSTRING_LENGTH < eIdx - sIdx + 1) {
                MAX_SUBSTRING_LENGTH = eIdx - sIdx + 1;
                LONGEST_SUBSTRING = str.substring(sIdx, eIdx+1);
            }
            // current substring is palindrome only when both conditions are satisfied
            res = true;
        }
        dp[sIdx][eIdx] = (res) ? 1 : 0;
        return res;
    }


    /*
     * ------------- Bottom Top DP solution ---------------------------------------
     * Since for a substring i, j we required i+1, j-1 case as well in recursive soln
     * Therefore, we'll compute from bottom to top 
     */


    private static String func2(String str) {
        int[][] dp = new int[str.length()][str.length()];


        // String ans = "";

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
                    // if (dp[k][j] == 1) { // we can create substring everytime - o(n) and return directly without computing afterwards.
                    //     ans = str.substring(k, j+1);
                    // }
                } else {
                    dp[k][j] = 0;
                }
            }
        }

        // loop top to down and compute the first occurence of 1
        for (int i=dp.length-1; i>=0; i--) {
            for (int k=0, j=i; k<dp.length && j<dp[0].length; k++, j++) {
                if (dp[k][j] == 1) {
                    return str.substring(k, j+1);
                }
            }
        }

        return str.substring(0, 1);
    }
}