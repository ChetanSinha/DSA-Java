package tries;

import java.util.Scanner;


public class JavaRough {

    final static boolean recursive = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();

        while (t != 0) {
            int n = scanner.nextInt();
            scanner.nextLine();
            Trie trie = new Trie();
            for (int i=0; i<n; i++) {
                trie.insert(scanner.nextLine(), recursive);
            }
            trie.print();
            System.out.println(trie.totalWords());
            t -= 1;
        }
        scanner.close();
    }
}