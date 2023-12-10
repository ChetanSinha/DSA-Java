import java.util.*;

public class DP_RegexPatternMatch {

    // file:///Users/chesin/Downloads/Telegram%20Desktop/Ace%20the%20Java%20Coding%20Interview/2.%20Data%20Structures/4.%20Strings/8.%20Regular%20Expression%20Matching%20in%20String.html

    // https://leetcode.com/problems/regular-expression-matching/description/

    // Regex Matching

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
        return regexMatching(str, pat, 0, 0, dp);
    }

    /*
     * Approach is same as wild card pattern matching problem except cases handling
     * 
     * cases that can arise here:
     * 
     * 1. next char in pat is '*' => then we need to handle zero or many for current char
     * 2. current char in pat is alphabet => check with current char in string
     * 3. current char in pat is '.' => match with any char in string
     * 
     * 
     * for case 1 we have two choices: 
     *      1. do not use * in pat (zero case), and move to next char
     *      2. use * in pat (many case), iff current char in pat is '.' or matches with current char in string 
     */
    private static boolean regexMatching(String str, String pat, Integer strIdx, Integer patIdx, int[][] dp) {
        if (strIdx >= str.length() && patIdx >= pat.length()) {
            // when both string and pattern got exhausted, true case
            return true;
        }
        
        if (strIdx < str.length() && patIdx >= pat.length()) {
            // when string is remaining but pattern got exhausted - false case
            return false;
        }

        if (strIdx >= str.length() && patIdx < pat.length()) {
            // when string got exhausted but pattern is remaining
            // true only if all the next chars in pat is like -> [any char]*
            // ie next two pairs are with *
            for (int i=patIdx; i<pat.length(); i+=2) {
                if (i+1 >= pat.length() || pat.charAt(i+1) != '*') {
                    // if there is no next element
                    // or next element is not '*' then return false
                    return false;
                }
            }

            return true;
        }

        if (dp[strIdx][patIdx] != -1) {
            return dp[strIdx][patIdx] == 1;
        }

        boolean res = false;
        if (patIdx + 1 < pat.length() && pat.charAt(patIdx+1) == '*') { // case 1
            res = regexMatching(str, pat, strIdx, patIdx+2, dp);
            if (str.charAt(strIdx) == pat.charAt(patIdx) || pat.charAt(patIdx) == '.') {
                res = res || regexMatching(str, pat, strIdx+1, patIdx, dp);
            }
        } else if (pat.charAt(patIdx) == str.charAt(strIdx) || pat.charAt(patIdx) == '.') { // case 2 or case 3
            res = regexMatching(str, pat, strIdx + 1, patIdx + 1, dp);
        }
        dp[strIdx][patIdx] = (res) ? 1 : 0;
        return res;
    }
}