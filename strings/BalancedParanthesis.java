import java.util.*;

public class BalancedParanthesis {

    // file:///Users/chesin/Downloads/Telegram%20Desktop/Ace%20the%20Java%20Coding%20Interview/2.%20Data%20Structures/4.%20Strings/20.%20Balanced%20Parentheses%20(hard).html
    
    static HashSet<String> res;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        // scanner.nextLine();

        while (t != 0) {
            int n = scanner.nextInt();
            // String str1 = scanner.nextLine();
            // String str2 = scanner.nextLine();
            func(n);
            // System.out.println("result: " + result);
            t -= 1;
        }
        scanner.close();
    }

    private static void func(int n) {
        res = new HashSet<>();
        balancedParathesis(n, "");
        System.out.println(res);
    }

    private static void balancedParathesis(int n, String str) {
        if (n == 0) {
            res.add(str);
            return;
        }

        if (str.length() == 0) {
            balancedParathesis(n-1, "()");
        } else {
            for (int i=0; i<str.length(); i++) {
                String s = "";
                if (i < str.length() - 1) {
                    s = str.substring(0, i+1) + "()" + str.substring(i+1);
                } else {
                    s = str + "()";
                }
                balancedParathesis(n-1, s);
            }
        }
        return;
    }
}