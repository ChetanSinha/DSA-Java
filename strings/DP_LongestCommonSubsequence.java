import java.util.*;

public class DP_LongestCommonSubsequence {
    // file:///Users/chesin/Downloads/Telegram%20Desktop/Ace%20the%20Java%20Coding%20Interview/2.%20Data%20Structures/4.%20Strings/15.%20Longest%20Common%20Subsequence.html
    // https://leetcode.com/problems/longest-common-subsequence/submissions/

    static int[][] dp;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        scanner.nextLine();

        while (t != 0) {
            String str1 = scanner.nextLine();
            String str2 = scanner.nextLine();
            Integer result = longestCommonSubsequence(str1, str2);
            System.out.println("result: " + result);
            t -= 1;
        }
        scanner.close();
    }

    /*
     * Bottom Up DP solution:
     * 
     * for index i, j for str1 and str2 respectively, two cases arises:
     *  
     * 1. str1[i] and str2[j] matches
     * 2. does not matches
     * 
     * CASE 1:
     *      - in this case, since current chars matches, in order to compute max length so far, we need to check for i-1, j-1 and add 1 to it (1 for i, j)
     *      - the result from here will be max so far
     * 
     * CASE 2:
     *      - since current chars does not matches, in order to compute max length so far, three cases arise
     *      - check for i-1, j-1 
     *      - check for i-1, j
     *      - check for i, j-1
     *      - max of all three will be max length found so far
     * 
     * 
     * We need to handle the base cases accordingly. 
     *        
     */


    private static Integer longestCommonSubsequence(String str1, String str2) {
        int[][] dp = new int[str1.length()][str2.length()];

        int maxLen = dp[0][0] = (str1.charAt(0) == str2.charAt(0)) ? 1 : 0;


        // pre compute first column
        for (int i=1; i<dp.length; i++) {
            if (str1.charAt(i) == str2.charAt(0)) {
                dp[i][0] = 1;
            } else {
                dp[i][0] = dp[i-1][0];
            }
            maxLen = Math.max(maxLen, dp[i][0]);
        }

        // pre compute first row
        for (int i=1; i<dp[0].length; i++) {
            if (str1.charAt(0) == str2.charAt(i)) {
                dp[0][i] = 1;
            } else {
                dp[0][i] = dp[0][i-1];
            }
            maxLen = Math.max(maxLen, dp[0][i]);
        }

        for (int i=1; i<dp.length; i++) {
            for (int j=1; j<dp[0].length; j++) {
                if (str1.charAt(i) == str2.charAt(j)) {
                    // case 1
                    dp[i][j] = dp[i-1][j-1]+1;
                } else {
                    // case 2
                    dp[i][j] = Math.max(dp[i-1][j-1], Math.max(dp[i-1][j], dp[i][j-1]));
               }
                maxLen = Math.max(maxLen, dp[i][j]);
            }
        }

        System.out.println("row:" + dp.length + " col:" + dp[0].length);

        for (int i=0; i<dp.length; i++) {
            for (int j=0; j<dp[0].length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }

        return maxLen;
    }
}