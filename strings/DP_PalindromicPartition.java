import java.util.*;

public class DP_PalindromicPartition {

    // file:///Users/chesin/Downloads/Telegram%20Desktop/Ace%20the%20Java%20Coding%20Interview/2.%20Data%20Structures/4.%20Strings/13.%20Palindromic%20Partitioning.html

    static List<List<String>> res = new ArrayList<>();
    static int[][] palindrome_dp;
    static int[] cuts_dp;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        scanner.nextLine();

        while (t != 0) {
            String str = scanner.nextLine();
            Integer result = partition2(str);
            System.out.println("result: " + result);
            t -= 1;
        }
        scanner.close();
    }


    /*
     * 
     * Palindrome paritioning 1:
     * 
     * find all the palindrome partitions possible for this string
     * https://leetcode.com/problems/palindrome-partitioning/submissions/
     * 
     * from each index idx, pick substring which is palindromic and move to remaining part of substring
     * 
     */

    public static List<List<String>> partition(String s) {
        List<String> ans = new ArrayList<>();
        palindrome_dp = new int[s.length()][s.length()];
        for (int i=0; i<palindrome_dp.length; i++) {
            for (int j=0; j<palindrome_dp[0].length; j++) {
                palindrome_dp[i][j] = -1;
            }
        }
        getParitions(s, 0, ans);
        return res;
    }

    private static void getParitions(String str, int idx, List<String> ans) {
        if (idx >= str.length()) {
            res.add(new ArrayList<>(ans));
            return;
        }

        for (int i=idx; i<str.length(); i++) {
            if (checkPalindrome(str, idx, i)) {
                ans.add(str.substring(idx, i+1));
                getParitions(str, i+1, ans);
                ans.remove(ans.size()-1);
            }
        }

        return;
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



    /*
     * Palindrome partitioning - 2
     * 
     * Find min cuts needed in string to divide into palindromes
     * 
     * https://leetcode.com/problems/palindrome-partitioning-ii/submissions/
     * 
     * same as palindrome partitioning 1
     * 
     * just here we change the return type to min cuts require to partition substring from idx to end
     * 
     * since here we don't want to get all possiblities, we dont use list that stores the string
     * memoize to optimize sol
     */

     public static Integer partition2(String s) {
        palindrome_dp = new int[s.length()][s.length()];
        cuts_dp = new int[s.length()];
        for (int i=0; i<s.length(); i++) {
            for (int j=0; j<s.length(); j++) {
                palindrome_dp[i][j] = -1;
            }
            cuts_dp[i] = -1;
        }
        return getParitions2(s, 0)-1;
    }
 
     private static Integer getParitions2(String str, int idx) {
        if (idx >= str.length()) {
            // MIN_CUT = Math.min(MIN_CUT, ans.size()-1);
            return 0;
        }

        if (cuts_dp[idx] != -1) {
            return cuts_dp[idx];
        }

        Integer res = Integer.MAX_VALUE;
        for (int i=idx; i<str.length(); i++) {
            if (checkPalindrome(str, idx, i)) {
                res = Math.min(res, getParitions2(str, i+1) + 1) ;
            }
        }
   
        return cuts_dp[idx] = res;
     }


    /*
     * Palindrome parition - 2 with tabular
     * 
     * same thing with tabulation - top to bottom
     * since we need to have result of i+1 before to compute results for i in recursive
     * we need to implement same in top to bottom 
     */

     public static Integer partition3(String s) {

        int[] cuts = new int[s.length()+1];
        cuts[s.length()] = 0; 

        palindrome_dp = new int[s.length()][s.length()];
        for (int i=0; i<palindrome_dp.length; i++) {
            for (int j=0; j<palindrome_dp[0].length; j++) {
                palindrome_dp[i][j] = -1;
            }
        }       

        for (int i=s.length()-1; i>=0; i--) {
            Integer res = Integer.MAX_VALUE;
            for (int j=i; j<s.length(); j++) {
                if (checkPalindrome(s, i, j)) {
                    res = Math.min(res, cuts[j+1] + 1);
                }
            }
            cuts[i] = res;
        }
        return cuts[0] - 1;
    }
}