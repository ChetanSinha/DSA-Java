package trees;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Scanner;

class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;
    TreeNode next;
}

// https://leetcode.com/problems/populating-next-right-pointers-in-each-node/solutions/4631610/java-beats-100/
// file:///Users/chesin/Downloads/Telegram%20Desktop/Ace%20the%20Java%20Coding%20Interview/2.%20Data%20Structures/6.%20Trees/12.%20Connect%20Same%20Level%20Siblings%20of%20a%20Binary%20Tree.html
public class BT_ConnectSameLevelSiblings {
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
            connectSameLevelSiblings(root);
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


    /*
     * Approach: Iterative
     * Connecting siblings at same level is nothing but traversing level order and adding next pointers
     * traversing level order involve using queue
     * But since here we need exactly those element in queue that are at same level, to maintain next pointer
     * we'll use second queue to add children nodes to it
     * 
     * while travelling on first queue (that contains all element at same level), we assign next pointer
     */
    private static void connectSameLevelSiblings(TreeNode root) {
        if (root == null) {
            return;
        }

        Queue<TreeNode> q1 = new LinkedList<>();
        q1.add(root);

        while (!q1.isEmpty()) {
            Queue<TreeNode> q2 = new LinkedList<>(); // maintain second queue for children
            TreeNode prev = null; // maintain prev pointer for next pointer assignment
            while (!q1.isEmpty()) { // travel first queue to assign next pointers
                TreeNode node = q1.poll(); // pop first queue item 
                if (node.left != null) {
                    q2.add(node.left); // add left child to second queue
                }
                if (node.right != null) { // add right child to second queue
                    q2.add(node.right);
                }
                if (prev == null) {
                    // if prev is null that means we are accessing first node of that level
                    prev = node;
                } else {
                    // else prev is assigned to a node, we need to assign next of that node to current node
                    prev.next = node;
                    // assign prev to current node
                    prev = node;
                }
            }
            // update q1 to q2 (for next level operation)
            q1 = q2;
        }
    }

    private static void printConnection(TreeNode root) {
        int height = getHeight(root);
        for (int i=1; i<= height; i++) {
            printLevelOrderRecursive(root, i);
            System.out.println();
        }
    }

    private static void printLevelOrderRecursive(TreeNode root, int level) {
        if (root == null) {
            return;
        }

        if (level == 1) {
            System.out.print(root.data + "->");
            if (root.next != null) {
                System.out.print(root.next.data + " ");
            } else {
                System.out.print("NULL");
            }
        } else if (level > 1) {
            printLevelOrderRecursive(root.left, level-1);
            printLevelOrderRecursive(root.right, level-1);
        }
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
     * for each levels (using height)
     * maintain global prev node
     * if prev is null, that means its first node of that level, assign prev to this node
     * if prev is not null, assign next of prev to this node and make this node as prev
     */

    static TreeNode prev;
    private static void connectSameLevelSiblingsRecursive(TreeNode root) {
        int height = getHeight(root);
        for (int i=1; i<= height; i++) {
            prev = null;
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
}