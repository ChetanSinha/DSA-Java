import java.util.*;

public class PrintAllPalindromeSubstring {
    // file:///Users/chesin/Downloads/Telegram%20Desktop/Ace%20the%20Java%20Coding%20Interview/2.%20Data%20Structures/4.%20Strings/7.%20Find%20all%20Palindrome%20Substrings.html

    // Find all palindromic sub strings
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();

        while (t != 0) {
            scanner.nextLine();
            String str = scanner.nextLine();
            func(str);
            t -= 1;
        }
        scanner.close();
    }

    // Logic - any string can have either even length palindrome or odd length palindrome
    // and these palindrome can be present at any char of the string
    // therefore for each char we need to check for both even length palindrome and odd length
    private static void func(String str) {
        for (int i=0; i<str.length(); i++) {
            // odd length (considering current char as center)
            checkPalindrome(str, i-1, i+1);
            // even length
            checkPalindrome(str, i, i+1);
        }
    }

    private static void checkPalindrome(String str, int start, int end) {
        if (start < 0 || end > str.length()-1) {
            return;
        }
        for (int i=start, j=end; i>=0 && j<str.length(); i--,j++) {
            if (str.charAt(i) != str.charAt(j)) {
                return;
            }
            // Printing every palindrome encountered
            System.out.println(str.substring(i, j+1));
        }
    }
}