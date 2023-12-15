import java.util.*;

class Pair {
    int x;
    int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}


public class WordSearch2 {


    // https://leetcode.com/problems/word-search-ii/description/
// file:///Users/chesin/Downloads/Telegram%20Desktop/Ace%20the%20Java%20Coding%20Interview/2.%20Data%20Structures/4.%20Strings/24.%20Boggle.html


// More optmial approach with trie possible.
    static int[][] direction = {
        {1, 0},
        {0, -1},
        {-1, 0},
        {0, 1}
        // {-1, -1},
        // {1, 1},
        // {-1, 1},
        // {1, -1}
    };

    static HashSet<String> res;

    static HashMap<String, Boolean> wordMap;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();

        while (t != 0) {
            char[][] board = new char[3][3];
            scanner.nextLine();
            for (int i=0; i<board.length; i++) {
                for (int j=0; j<board[0].length; j++) {
                    board[i][j] = scanner.next().charAt(0);
                }
            }

            int n = scanner.nextInt();
            scanner.nextLine();
            String[] dictionary = new String[n];
            for (int i=0; i<dictionary.length; i++) {
                dictionary[i] = scanner.nextLine();
            }

            func1(board, dictionary);
            t -= 1;
        }
        scanner.close();
    }


    /*
     * Word search - 2
     * 
     * Un optimized version
     */

    private static void func(char[][] board, String[] words) {
        res = new HashSet<>();
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[0].length; j++) {
                boogle(board, words, i, j, "");
            }
        }
        System.out.println(res);
    }

    private static void boogle(char[][] board, String[] words, int x, int y, String ans) {
        if (x < 0 || y < 0 || x >= board.length || y >= board[0].length || board[x][y] == '#' || !checkIfValidStartsWith(ans + board[x][y], words)) {
            return;
        }

        if (checkIfValidWord(ans + board[x][y], words)) {
            res.add(ans + board[x][y]);
        }
        
        char temp = board[x][y];
        board[x][y] = '#';
        for (int i=0; i<direction.length; i++) {
            int row = x + direction[i][0];
            int col = y + direction[i][1];
            boogle(board, words, row, col, ans + temp);
        }
        board[x][y] = temp;
    }

    private static boolean checkIfValidWord(String str, String[] words) {
        if (str.isEmpty()) {
            return false;
        }
        for (String word: words) {
            if (str.equals(word))
                return true;
        }
        return false;
    }

    private static boolean checkIfValidStartsWith(String str, String[] words) {
        if (str.isEmpty()) {
            return false;
        }
        for (String word: words) {
            if (word.startsWith(str))
                return true;
        }
        return false;
    }

    /*
     * 
     * Word Search - 2 (Partial optimized)
     * 
     * Create char to coord map
     * 
     * for each string in words, based on above map, make dfs search. 
     */


    private static void func1(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        HashMap<Character, List<Pair>> wordStartMap = new HashMap<>();


        // Char to Coord MAP
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                Pair pair = new Pair(i, j);
                char currentChar = board[i][j];

                if (wordStartMap.containsKey(currentChar)) {
                    wordStartMap.get(currentChar).add(pair);
                } else {
                    List<Pair> list = new ArrayList<>();
                    list.add(pair);
                    wordStartMap.put(currentChar, list);
                }
            }
        }

        // for each string search
        for (int i=0; i<words.length; i++) {
            if (performWordSearch(board, words[i], wordStartMap, -1, -1, 0)) {
                res.add(words[i]);
            }
        }
        System.out.println(res);
    }

    private static boolean performWordSearch(char[][] board, String word, HashMap<Character, List<Pair>> wordStartMap, int x, int y, int k) {
        if (x >= 0 && y >= 0 && x < board.length && y < board[0].length && board[x][y] == '#') {
            // visited case
            return false;
        }

        if (k >= word.length()) {
            // all chars of this word found
            return true;
        }

        if (!wordStartMap.containsKey(word.charAt(k))) {
            // no char exists in map
            return false;
        }


        // for all coords of this char in map, check if any lies in valid direction of current coord, search ahead if yes.
        List<Pair> coords = wordStartMap.get(word.charAt(k));
        for (int i=0; i<coords.size(); i++) {
            int row = coords.get(i).x;
            int col = coords.get(i).y;

            boolean isValidCoord = false;
            for (int j=0; j<direction.length; j++) {
                if ((x == -1 && y == -1) || (row == x+direction[j][0] && col == y+direction[j][1])) {
                    isValidCoord = true;
                    break;
                }
            }
            if (isValidCoord) {
                char temp = '#';
                if (x != -1 || y != -1) {
                    temp = board[x][y];
                    board[x][y] = '#';
                }
                if (performWordSearch(board, word, wordStartMap, row, col, k+1)) {
                    if (x != -1 || y != -1) {
                        board[x][y] = temp;
                    }
                    return true;
                }
                if (x != -1 || y != -1) {
                    board[x][y] = temp;
                }
            }
        }
        return false;
    }
}