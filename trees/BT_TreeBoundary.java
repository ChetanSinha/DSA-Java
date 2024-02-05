package trees;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// file:///Users/chesin/Downloads/Telegram%20Desktop/Ace%20the%20Java%20Coding%20Interview/2.%20Data%20Structures/6.%20Trees/11.%20Print%20Tree%20Perimeter.html

// print tree boundaries -> left, leaf, right
public class BT_TreeBoundary {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();

        while (t != 0) {
            int n = scanner.nextInt();
            List<Integer> arr = new ArrayList<>();
            BinaryTree<Integer> tree = new BinaryTree<>();
            for (int i=0; i<n; i++) {
                arr.add(getNextNode(scanner));
            }
            tree.createTree(arr);
            tree.printInOrder(false);
            printTreeBoundary(tree);
            printTreeBoundary2(tree);
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


    /*
     * This prints boundaries in three steps:
     *  1. Print just the left boundary - top to bottom (except leaf nodes)
     *  2. Print all leaf nodes
     *  3. Print right boundary - bottom to top (except leaf node)
     */
    private static void printTreeBoundary(BinaryTree<Integer> tree) {
        if (tree.root == null) {
            return;
        }
        System.out.print(tree.root.data + "-");
        printLeftBoundary(tree.root.left);
        printLeafBoundary(tree.root);
        printRightBoundary(tree.root.right);
        System.out.println();
    }

    private static void printLeftBoundary(Node<Integer> root) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            return;
        }
        System.out.print(root.data + "-"); // top to bottom

        // case when left is not present, then right child will be boundary
        if (root.left != null) {
            printLeftBoundary(root.left);
        } else {
            printLeftBoundary(root.right);
        }
    }

    private static void printLeafBoundary(Node<Integer> root) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            System.out.print(root.data + "-");
            return;
        }
        printLeafBoundary(root.left);
        printLeafBoundary(root.right);
    }


    private static void printRightBoundary(Node<Integer> root) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            return;
        }

        // case when right is not present, then left child will be boundary
        if (root.right != null) {
            printRightBoundary(root.right);
        } else {
            printRightBoundary(root.left);
        }
        System.out.print(root.data + "-"); // bottom to top
    }



    /*
     * Uses just one function to print boundaries
     * two calls for left subtree and right subtree
     * for left subtree:
     *  - node will be printed first, then traversing will be done to other nodes
     * 
     * for right subtree:
     *  - node will be printed after traversing is done to other nodes
     */

    private static void printTreeBoundary2(BinaryTree<Integer> tree) {
        System.out.println("------------------");
        System.out.print(tree.root.data + "-");
        printBoundaries(tree.root.left, true, false);
        printBoundaries(tree.root.right, false, true);
        System.out.println();
    }

    private static void printBoundaries(Node<Integer> root, boolean left, boolean right) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            // leaf nodes
            System.out.print(root.data + "-");
            return;
        }

        // left = true; right = false -> left boundary 
        // left = false; right = true -> right boundary
        // left = false; right = false -> leaf nodes traversing

        if (left) {
            // node printed before traversing for top to bottom in left boundary
            System.out.print(root.data + "-"); 
            if (root.left != null) {
                // we need to travel both, left and right
                // left - for printing left boundary
                // right - for printing leaf nodes in the right subtree
                printBoundaries(root.left, true, false);
                printBoundaries(root.right, false, false);
            } else if (root.right != null) {
                // case when there is no left subtree then right subtree will be the boundary
                printBoundaries(root.right, true, false);
            }
        } else if (right) {
            if (root.right != null) {
                // same as for left both left and right traverse is done
                // left - for printing leaf nodes in left subtree
                // right - for printing right boundary
                // Note here we visit left first, coz leaf of left will come first in order
                printBoundaries(root.left, false, false);
                printBoundaries(root.right, false, true);
            } else if (root.left != null) {
                // case when no right child exists
                printBoundaries(root.left, false, true);
            }

            // node printed after traversing for bottom to top in right boundary
            System.out.print(root.data + "-");
        } else {
            // case for traversing leaf nodes
            printBoundaries(root.left, false, false);
            printBoundaries(root.right, false, false);
        }
    }
}