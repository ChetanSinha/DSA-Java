import java.util.*;

public class WordBreak {
    // file:///Users/chesin/Downloads/Telegram%20Desktop/Ace%20the%20Java%20Coding%20Interview/2.%20Data%20Structures/4.%20Strings/5.%20Word%20Break%20Problem.html
    // check if words in string are from dictionary

    static HashMap<String, Boolean> map;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();

        while (t != 0) {
            int m = scanner.nextInt(); 
            scanner.nextLine();
            List<String> dictionary = new ArrayList<>();
            for (int i=0; i<m; i++) {
                dictionary.add(scanner.nextLine().trim());
            }
            String str = scanner.nextLine();
            map = new HashMap<>();
            // boolean result = func(str, dictionary, 0);
            boolean result = func1(str, dictionary);
            System.out.println("result: " + result);
            t -= 1;
        }
        scanner.close();
    }



    // Method1 - at each recursive call, check if string starts with any of the words in dictionary.
    // if yes, then check for the remaining part of string (by reducing the subspace)
    private static boolean func1(String str, List<String> dict) {
        if (str.length() <= 0) {
            return true;
        }

        // memoized results
        if (map.containsKey(str)) {
            return map.get(str);
        }
        
        for (String word : dict) {
            if (str.startsWith(word)) {
               boolean result = func1(str.substring(word.length()), dict);
               if (result == true) {
                map.put(str, true);
                return true;
               }
            }
        }
        map.put(str, false);
        return false;
    }

    // Method2 - for each index in string, check for substring that matches word in dictionary
    // if yes - check for remaining part of sub string
    private static boolean func(String str, List<String> dict) {
        if (map.containsKey(str)) {
            return map.get(str);
        }

        for (int i=1; i<=str.length(); i++) {
            String first = str.substring(0, i);
            if (dict.contains(first)) {
                String second = str.substring(first.length(), str.length());
                if (second.length() == 0 || dict.contains(second) || func(second, dict)) {
                    map.put(str, true);
                    return true;
                }
            }
        }
        map.put(str, false);
        return false;
    }
}