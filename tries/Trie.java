package tries;

public class Trie {

    TrieNode root;

    public static class TrieNode {
        TrieNode[] child = new TrieNode[26];
        boolean isEnd; 
        public TrieNode() {
            isEnd = false;
        }
        public boolean isChildEmpty() {
            for (int i=0; i<child.length; i++) {
                if (child[i] != null) {
                    return false;
                }
            }
            return true;
        }
    }

    public void insert(String str, boolean recursive) {
        if (str == null) {
            return;
        }
        str = str.toLowerCase();
        root = (recursive) ? insertRecursive(root, str, 0) : insertIterative(root, str);
    }

    private TrieNode insertRecursive(TrieNode root, String str, int idx) {
        if (root == null) {
            root = new TrieNode();
        }
        if (idx == str.length()) {
            root.isEnd = true;
            return root;
        }
        int alphaIdx = str.charAt(idx) - 'a';
        root.child[alphaIdx] = insertRecursive(root.child[alphaIdx], str, idx + 1);
        return root;
    } 


    private TrieNode insertIterative(TrieNode root, String str) {
        if (root == null) {
            root = new TrieNode();
        }
        TrieNode temp = root;
        for (int i=0; i<str.length(); i++) {
            if (temp.child[str.charAt(i) - 'a'] == null) {
                temp.child[str.charAt(i) - 'a'] = new TrieNode();
            }
            temp = temp.child[str.charAt(i) - 'a'];
        }
        temp.isEnd = true;
        return root;
    }

    public boolean search(String str, boolean recursive) {
        if (str == null) {
            return false;
        } 
        str = str.toLowerCase();
        return (recursive) ? searchRecursive(root, str, 0) : searchIterative(root, str);
    }

    private boolean searchRecursive(TrieNode root, String str, int idx) {
        if (root == null) {
            return false;
        }
        if (idx == str.length()) {
            return root.isEnd == true;
        }
        return searchRecursive(root.child[str.charAt(idx) - 'a'], str, idx+1);
    }

    private boolean searchIterative(TrieNode root, String str) {
        if (root == null) {
            return false;
        }
        TrieNode temp = root;
        for (int i=0; i<str.length(); i++) {
            if (temp.child[str.charAt(i) - 'a'] == null) {
                return false;
            }
            temp = temp.child[str.charAt(i) - 'a'];
        }
        return temp.isEnd == true;
    }

    public void print() {
        System.out.println("Printing trie strings......");
        print(root, "");
    }

    // prints in sorted order of strings in trie
    private void print(TrieNode root, String str) {
        if (root == null) {
            return;
        }
        if (root.isEnd) {
            System.out.println(str);
        }
        for (int i=0; i<root.child.length; i++) {
            if (root.child[i] != null) {
                print(root.child[i], str + (char)('a' + i));
            }
        }
    }

    public void delete(String str) {
        if (str == null) {
            return;
        }
        str = str.toLowerCase();
        deleteRecursive(root, str, 0);
    }


    /*
     * Three cases for deletion:
     *  1. The trie to be deleted for a string is independent of any other trie -> delete every node of this trie
     *  2. The trie to be deleted is part of larger trie -> just mark end of this trie as false
     *  3. This trie contains other smaller tries -> delete nodes between smaller trie end and this trie end.
     */
    private TrieNode deleteRecursive(TrieNode root, String str, int idx) {
        if (root == null) {
            return null;
        }
        if (idx == str.length()) {
            if (!root.isChildEmpty()) { // case 2
                root.isEnd = false;
                return root;
            }
            return root = null; // case 1 & 3
        }
        int alphaIdx = str.charAt(idx) - 'a';
        root.child[alphaIdx] = deleteRecursive(root.child[alphaIdx], str, idx + 1);
        if (root.isChildEmpty()) {
            root = null;
            return null;
        }            
        return root;
    }

    public int totalWords() {
        return totalWords(root);
    }

    private int totalWords(TrieNode root) {
        if (root == null) {
            return 0;
        }
        int count = 0;
        if (root.isEnd) {
            count += 1;
        }
        for (int i=0; i<root.child.length; i++) {
            if (root.child[i] != null) {
                count += totalWords(root.child[i]);
            }
        }
        return count;
    }
}
