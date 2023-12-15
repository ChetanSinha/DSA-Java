import java.util.*;

public class DP_LongestCommonSubstring {

    static int[][] dp;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        scanner.nextLine();

        while (t != 0) {
            String str1 = scanner.nextLine();
            String str2 = scanner.nextLine();
            Integer result = longestCommonSubstring2(str1, str2);
            System.out.println("result: " + result);
            t -= 1;
        }
        scanner.close();
    }

    /*
     * 
     * Bottom up Recursive memoized solution
     * 
     * for index i, j of str1, and str2 respec, two cases
     * 
     * 1. str1[i] = str2[j]
     * 2. str1[i] != str2[j]
     * 
     * basically, sub problem (i, j) gives longest substring for string: str1[0 to i] and str2[0 to j]
     * 
     * Maintain counter, to maintain the number of substrings found so far.
     * 
     * CASE 1:
     *      - since current is same, we need to check for next indexes - i+1, j+1
     * 
     * CASE 2: 
     *      - since current is not same, we need to check i+1, j and i, j+1
     *      - no need to check for i+1, j+1 as both will cover this
     *      - since we are maintaining substring count with count variable, and current is not same, we pass 0 as count for next iteration
     * 
     */

    private static Integer func(String str1, String str2) {
        dp = new int[str1.length()][str2.length()];
        for (int i=0; i<dp.length; i++) {
            for (int j=0; j<dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }
        return longestCommonSubstring(str1, str2, 0, 0, 0);
    }

    private static Integer longestCommonSubstring(String str1, String str2, int s1Idx, int s2Idx, int count) {
        if (dp[s1Idx][s2Idx] != -1) {
            return dp[s1Idx][s2Idx];
        }

        if (s1Idx >= str1.length() || s2Idx >= str2.length()) {
            return dp[s1Idx][s2Idx] = count;
        }

        if (str1.charAt(s1Idx) == str2.charAt(s2Idx)) { // case 1
            count = longestCommonSubstring(str1, str2, s1Idx+1, s2Idx+1, count+1);
        }

        int c1 = longestCommonSubstring(str1, str2, s1Idx+1, s2Idx, 0);
        int c2 = longestCommonSubstring(str1, str2, s1Idx, s2Idx+1, 0);

        return dp[s1Idx][s2Idx] = Math.max(count, Math.max(c1, c2));
    }


    /*
     * 
     * Bottom up DP soln
     * 
     * Same as longest subsequence except, here we make entry 0, when string dont match
     * to ensure continuity of substrings
     */


    private static Integer longestCommonSubstring2(String str1, String str2) {
        int[][] dp = new int[str1.length()][str2.length()];

        int maxLen = 0;
        for (int i=0; i<dp.length; i++) {
            if (str1.charAt(i) == str2.charAt(0)) {
                dp[i][0] = 1;
            } else {
                dp[i][0] = 0;
            }
            maxLen = Math.max(maxLen, dp[i][0]);
        }

        for (int i=0; i<dp[0].length; i++) {
            if (str1.charAt(0) == str2.charAt(i)) {
                dp[0][i] = 1;
            } else {
                dp[0][i] = 0;
            }
            maxLen = Math.max(maxLen, dp[0][i]);
        }

        for (int i=1; i<dp.length; i++) {
            for (int j=1; j<dp[0].length; j++) {
                if (str1.charAt(i) == str2.charAt(j)) {
                    dp[i][j] = dp[i-1][j-1]+1;
                } else {
                    dp[i][j] = 0;
               }
                maxLen = Math.max(maxLen, dp[i][j]);
            }
        }

        return maxLen;
    }
}