import java.util.*;

public class DP_WildCardPattern {

//    https://leetcode.com/problems/wildcard-matching/description/

//    wildcard pattern matching
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        scanner.nextLine();

        while (t != 0) {
            String str = scanner.nextLine();
            String pat = scanner.nextLine();
            boolean result = func(str, pat);
            System.out.println("result: " + result);
            t -= 1;
        }
        scanner.close();
    }


    private static boolean func(String str, String pat) {
        int[][] dp = new int[str.length()][pat.length()];
        for (int i=0; i<str.length(); i++) {
            for (int j=0; j<pat.length(); j++) {
                dp[i][j] = -1;
            }
        }
        return wildcardMatching(str, pat, str.length()-1, pat.length()-1, dp);
    }

/*
there could be three cases in pattern matching:
    1. current char in pattern is '?' => move to next element in string
    2. current char in pattern is alphabet => check if it matches with current char in string
    3. current char in pattern is '*' => here we need to check all possible combinations

    there are two possibilities for case 3:
        1. to take current element in string with * in pattern
        2. to do not take current element in string with * in pattern
*/
    private static boolean wildcardMatching(String str, String pat, Integer strIdx, Integer patIdx, int[][] dp) {
        if (strIdx < 0 && patIdx < 0) return true; // both string and pattern got exhausted
        if (strIdx >= 0 && patIdx < 0) return false; // pattern got exhausted but string is remaining - false case
        if (strIdx < 0 && patIdx >= 0) { // string got exhausted but pattern is remaining - only true if all remaining in pattern is *
            for (int i=0; i<=patIdx; i++) {
                if (pat.charAt(i) != '*') return false;
            }
            return true;
        }

        if (dp[strIdx][patIdx] != -1) {
            return dp[strIdx][patIdx] == 1;
        }

        boolean val = false;
        if (str.charAt(strIdx) == pat.charAt(patIdx) || pat.charAt(patIdx) == '?') { // case 1 and case 2
            val = wildcardMatching(str, pat, strIdx-1, patIdx-1, dp);
        } else if (pat.charAt(patIdx) == '*') { // case 3
            val = wildcardMatching(str, pat, strIdx-1, patIdx, dp) || wildcardMatching(str, pat, strIdx, patIdx-1, dp);
        }
        dp[strIdx][patIdx] = (val) ? 1 : 0;
        return val;
    }
}