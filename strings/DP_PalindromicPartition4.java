import java.util.*;

public class DP_PalindromicPartition4 {

    // https://leetcode.com/problems/palindrome-partitioning-iv/description/
    
    static int[][] palindrome_dp;
    static int DESIRED_PARTITIONS = 3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        scanner.nextLine();

        while (t != 0) {
            String str = scanner.nextLine();
            boolean result = partition(str);
            System.out.println("result: " + result);
            t -= 1;
        }
        scanner.close();
    }


    /*
     * 
     * Palindrome paritioning 4:
     * 
     * check if palindrome partition of 3 is possible or not
     * 
     * just modifying the approach for palindrome partitioning 1 to cater ans string with 3 elements in it.
     * instead of maintaining array, we can use variable to maintain how much partitioning is done.
     * 
     * for last partition (ie 2 partitions is done, and now 3 needs to be done)
     * we know that this could true iff remaining string is palindrome
     * 
     */

    public static boolean partition(String s) {
        palindrome_dp = new int[s.length()][s.length()];
        for (int i=0; i<palindrome_dp.length; i++) {
            for (int j=0; j<palindrome_dp[0].length; j++) {
                palindrome_dp[i][j] = -1;
            }
        }
        return getParitions(s, 0, 0);
    }

    private static boolean getParitions(String str, int idx, Integer partitions) {
        if (idx >= str.length()) {
            return (partitions == 3);
        }

        if (partitions == DESIRED_PARTITIONS - 1) {
            // optimization for last partition
            if (checkPalindrome(str, idx, str.length()-1)) {
                return true;
            }
        } else {
            for (int i=idx; i<str.length(); i++) {
                if (checkPalindrome(str, idx, i)) {
                    if (getParitions(str, i+1, partitions+1)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean checkPalindrome(String str, int i, int j) {
        if (palindrome_dp[i][j] != -1) {
            return palindrome_dp[i][j] == 1;
        }

        if (j <= i) {
            palindrome_dp[i][j] = 1;
            return true;
        }

        boolean res = false;
        if (str.charAt(i) == str.charAt(j))
            res = checkPalindrome(str, i+1, j-1);

        palindrome_dp[i][j] = (res) ? 1 : 0;
        return res;
    }
}