import java.util.Scanner;

public class ReverseWords {
    // file:///Users/chesin/Downloads/Telegram%20Desktop/Ace%20the%20Java%20Coding%20Interview/2.%20Data%20Structures/4.%20Strings/2.%20Reverse%20Words%20in%20a%20Sentence.html
    // Reverse words
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

    private static void func(String str) {
        str = reverseStr(str, 0, str.length()-1);

        int wordStart = 0;
        int wordEnd = 0;

        while (wordEnd < str.length()) {
            if (str.charAt(wordEnd) != ' ') {
                wordEnd += 1;
            } else {
                str = reverseStr(str, wordStart, wordEnd-1);
                while (str.charAt(wordEnd) == ' ' && wordEnd < str.length()) {
                    wordEnd += 1;
                } 
                wordStart = wordEnd;
            }
        }
        str = reverseStr(str, wordStart, wordEnd-1);
        System.out.println(str);
    }

    private static String reverseStr(String str, int start, int end) {
        // using char array as strings are immutable
        char[] charArray = str.toCharArray();
        while (start < end) {
            char temp = charArray[start];
            charArray[start] = charArray[end];
            charArray[end] = temp;
            start += 1;
            end -= 1;
        }
        return new String(charArray);
    }
}