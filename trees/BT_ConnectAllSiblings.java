package trees;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;
    TreeNode next;
}

//  file:///Users/chesin/Downloads/Telegram%20Desktop/Ace%20the%20Java%20Coding%20Interview/2.%20Data%20Structures/6.%20Trees/13.%20Connect%20All%20Siblings%20of%20a%20Binary%20Tree.html

// Connect all siblings
public class BT_ConnectAllSiblings {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();

        while (t != 0) {
            int n = scanner.nextInt();
            List<Integer> arr = new ArrayList<>();
            for (int i=0; i<n; i++) {
                arr.add(getNextNode(scanner));
            }
            TreeNode root = createTree(arr, 0);
            printInOrderRecursive(root);
            System.out.println();
            connectAllSiblingsRecursive(root);
            printConnection(root);
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

    private static TreeNode createTree(List<Integer> values, int index) {
        if (index < values.size() && Objects.nonNull(values.get(index))) {
            TreeNode node = new TreeNode();
            node.data = values.get(index);
            node.left = createTree(values, 2*index+1);
            node.right = createTree(values, 2*index+2);
            node.next = null;
            return node;
        }
        return null;
    }
    
    private static void printInOrderRecursive(TreeNode root) {
        if (root == null) {
            return;
        }
        printInOrderRecursive(root.left);
        System.out.print(root.data + " ");
        printInOrderRecursive(root.right);
    }

    private static int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getHeight(root.left);
        int right = getHeight(root.right);
        return Math.max(left, right) + 1;
    } 




    /*
     * Recursive : Space - O(height) [recursive stack] | Time - O(n)
     * get height of the tree first
     * same as connect all same level siblings
     * except instead of modifying prev at each level, we wont modify that
     * we keep kniting the connection with prev
     */

    static TreeNode prev;
    private static void connectAllSiblingsRecursive(TreeNode root) {
        int height = getHeight(root);
        prev = null;
        for (int i=1; i<= height; i++) {
            levelOrderConnect(root, i);
        }
    }

    private static void levelOrderConnect(TreeNode root, int level) {
        if (root == null) {
            return;
        }

        if (level == 1) {
            if (prev == null) {
                prev = root;
            } else {
                prev.next = root;
                prev = root;
            }
        } else if (level > 1) {
            levelOrderConnect(root.left, level-1);
            levelOrderConnect(root.right, level-1);
        }
    }

    private static void printConnection(TreeNode root) {
        if (root == null) {
            System.out.println("NULL");
            return;
        }
        System.out.print(root.data + "->");
        printConnection(root.next);
    }
}