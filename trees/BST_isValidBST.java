package trees;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// https://leetcode.com/problems/validate-binary-search-tree/
// file:///Users/chesin/Downloads/Telegram%20Desktop/Ace%20the%20Java%20Coding%20Interview/2.%20Data%20Structures/6.%20Trees/9.%20Is%20a%20Binary%20Search%20Tree%20Valid%3F.html

// Check if Binary tree is BST or not
public class BST_isValidBST {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();

        while (t != 0) {
            int n = scanner.nextInt();
            List<Integer> arr = new ArrayList<>();
            BinaryTree<Integer> tree = new BinaryTree<>();
            for (int i=0; i<n; i++) {
                arr.add(scanner.nextInt());
            }
            tree.createTree(arr);
            tree.printInOrder(false);
            System.out.println(isValidBST(tree)); 
            t -= 1;
        }
        scanner.close();
    }

    private static boolean isValidBST(BinaryTree<Integer> tree) {
        return func(tree.root, Long.MIN_VALUE, Long.MAX_VALUE);
    }


    /*
     * Approach:
     * A valid BST has:
     *  - Max in left subtree must be less than current node
     *  - Min in right subtree must be greater than current node
     * 
     * To check this while traversal, we can pass min max according to the subtree being traversed
     * 
     * if we are traversing left subtree then any node in left cannot be greater than current node therefore pass max as current node
     * if we are traversing right subtree then any node in right cannot be less than current node therefore pass min as current node
     * 
     */
    private static boolean func(Node<Integer> root, long min, long max) {
        if (root == null) {
            return true;
        } 

        if (root.data >= max || root.data <= min) {
            return false;
        }

        return func(root.left, min, root.data) && func(root.right, root.data, max);
    }
}