import java.util.*;

public class WordSearch {

    // https://leetcode.com/problems/word-search/submissions/
    static int[][] direction = {
        {1, 0},
        {0, -1},
        {-1, 0},
        {0, 1}
    };

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
            String word = scanner.nextLine();
            func(board, word);
            t -= 1;
        }
        scanner.close();
    }

    /*
     * 
     * Word search - 1
     * 
     * Backtracking search
     * 
     */


    public static boolean modifiedDFS(char[][] board, String word, int k, int x, int y) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] == '#' || board[x][y] != word.charAt(k)) {
            return false;
        }

        if (k == word.length()-1) {
            return true;
        }

        // exlcuding current element for future searches
        char temp = board[x][y];
        board[x][y] = '#';
        for (int i=0; i<direction.length; i++) {
            int row = x + direction[i][0];
            int col = y + direction[i][1];
            if (modifiedDFS(board, word, k+1, row, col)) {
                return true;
            }
        }
        board[x][y] = temp;
        return false;
    }

    public static boolean func(char[][] board, String word) {
        if (word.length() < 1) {
            return true;
        }

        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (modifiedDFS(board, word, 0, i, j)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}