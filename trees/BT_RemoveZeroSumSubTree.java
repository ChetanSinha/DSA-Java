package trees;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BT_RemoveZeroSumSubTree {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();

        while (t != 0) {
            int n = scanner.nextInt();
            List<Integer> arr = new ArrayList<>();
            for (int i=0; i<n; i++) {
                arr.add(getNextNode(scanner));
            }
            BinaryTree<Integer> tree = new BinaryTree<>();
            tree.createTree(arr);
            tree.printInOrder(true);
            removeZeroSumSubTree(tree.root);
            t -= 1;
        }
        scanner.close();
    }

    public static Integer getNextNode(Scanner scanner) {
        String userInput = scanner.next();
        if (userInput.equalsIgnoreCase("null")) {
            return null;
        } else {
            int intValue = Integer.parseInt(userInput);
            return intValue;
        }
    }

    private static void removeZeroSumSubTree(Node<Integer> root) {
        int rootSum = func(root);
        if (rootSum == 0) {
            root = null;
        }
        printInOrder(root);
        System.out.println();
    }


    /*
     * compute sum of each subtree
     * if subtree sum is zero, make parent's reference to null
     */
    private static int func(Node<Integer> root) {
        if (root == null) {
            return 0;
        }
        int left = func(root.left);
        if (left == 0) {
            root.left = null;
        }
        int right = func(root.right);
        if (right == 0) {
            root.right = null;
        }
        return root.data + left + right;
    }

    private static void printInOrder(Node<Integer> root) {
        if (root == null) {
            return;
        }
        printInOrder(root.left);
        System.out.print(root.data + " ");
        printInOrder(root.right);
    }
}