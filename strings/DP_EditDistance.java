import java.util.*;

public class DP_EditDistance {

    // https://leetcode.com/problems/edit-distance/description/

    // Min distance to convert word1 to word2

    static int[][] dp;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        scanner.nextLine();
        while (t != 0) {
            String word1 = scanner.nextLine();
            String word2 = scanner.nextLine();
            Integer res = func2(word1, word2);
            System.out.println("result: " + res);
            t -= 1;
        }
        scanner.close();
    }


    /*
     * Recursive Solution With memoization
     * 
     * Problem involves string matching between two strings
     * two cases possible while string matching:
     * 1. current char at both string matches
     * 2. current char does not matches
     * 
     * 
     * CASE1: for current idx i,j we need to search on i-1 and j-1
     * CASE2: since current char does not matches we need to perform all the allowed operations, to search on all answers
     *      allowed operations:
     *          1. insert - i (idx of word1) will remain as it is, j (idx of word2) will decrement as we've inserted char at j
     *          2. delete - i will decrement, j will remain as it is (char at i is deleted)
     *          3. replace - both i and j will decrement, as char at i is replaced with char at j
     *  
     */
    private static Integer func(String word1, String word2) {
        dp = new int[word1.length()][word2.length()];

        for (int i=0; i<dp.length; i++) {
            for (int j=0; j<dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }
        return getMinDistance(word1, word2, word1.length()-1, word2.length()-1);
    }

    private static Integer getMinDistance(String word1, String word2, int idx1, int idx2) {
        if (idx1 < 0 && idx2 < 0) {
            return 0;
        }

        if (idx1 < 0) {
            return idx2 + 1;
        }

        if (idx2 < 0) {
            return idx1 + 1;
        }

        if (dp[idx1][idx2] != -1) {
            return dp[idx1][idx2];
        }

        if (word1.charAt(idx1) == word2.charAt(idx2)) {
            return dp[idx1][idx2] = getMinDistance(word1, word2, idx1-1, idx2-1);
        }

        return dp[idx1][idx2] =  Math.min(getMinDistance(word1, word2, idx1-1, idx2), Math.min(getMinDistance(word1, word2, idx1, idx2-1), getMinDistance(word1, word2, idx1-1, idx2-1))) + 1;
    }


    /*
     * 
     * Tabular solution - Bottom up
     * 
     * tabular solution of recursive solution
     */

    private static Integer func2(String word1, String word2) {
        int[][] dp = new int[word1.length()+1][word2.length()+1];

        for (int i=0; i<dp.length; i++) {
            for (int j=0; j<dp[0].length; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = (j == 0) ? i : j;
                } else {
                    if (word1.charAt(i-1) == word2.charAt(j-1)) {
                        dp[i][j] = dp[i-1][j-1];
                    } else {
                        dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
                    }
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }
}