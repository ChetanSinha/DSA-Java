import java.util.*;

public class GeneralizedAbbrevation {

    // file:///Users/chesin/Downloads/Telegram%20Desktop/Ace%20the%20Java%20Coding%20Interview/2.%20Data%20Structures/4.%20Strings/20.%20Balanced%20Parentheses%20(hard).html
    
    static List<String> res;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        scanner.nextLine();

        while (t != 0) {
            String str1 = scanner.nextLine();
            // String str2 = scanner.nextLine();
            func(str1);
            // System.out.println("result: " + result);
            t -= 1;
        }
        scanner.close();
    }

    private static void func(String str) {
        res = new ArrayList<>();
        generalizedAbbrevation(str, 0, "");
        System.out.println(res);
    }

    private static void generalizedAbbrevation(String str, int idx, String ans) {
        if (idx >= str.length()) {
            res.add(ans);
            return;
        }

        generalizedAbbrevation(str, idx+1, ans + str.charAt(idx));
        int numeric = 1;
        if (ans.length() > 0 && ans.charAt(ans.length()-1) >= '0' && ans.charAt(ans.length()-1) <= '9') {
            char[] charArray = ans.toCharArray();
            int prevNumeric = charArray[ans.length()-1] - '0';
            numeric += prevNumeric;
            charArray[ans.length()-1] = (char) (numeric + '0');
            ans = new String(charArray);
        } else {
            ans = ans + numeric;
        }
        generalizedAbbrevation(str, idx+1, ans);
    }
}