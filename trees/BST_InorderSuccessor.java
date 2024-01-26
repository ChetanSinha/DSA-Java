package trees;


import java.util.Scanner;

public class BST_InorderSuccessor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();

        while (t != 0) {
            int n = scanner.nextInt();
            BST tree = new BST();
            for (int i=0; i<n; i++) {
                tree.insertBST(scanner.nextInt());
            }
            tree.printInOrder(true);
            int val = scanner.nextInt();
            int ans = inorderSuccessor(tree, val);
            System.out.println(ans);
            t -= 1;
        }
        scanner.close();
    }

    private static int inorderSuccessor(BST tree, Integer val) {
        return perform(tree.root, val);
    }


    /*
     * Logic here is:
     *  1) if current <= val - check on right
     *  2) if current > val - check on left 
     * 
     * since we need to find successor, current node could be successor only when val lies in the left subtree
     * therefore from the result of left subtree
     *  - if returned is -1 that means, successor is not yet found, and current node is first node that is visited after val, 
     *      therefore current node is the sucessor
     *  - if returned is not -1 that means, successor has been found, therefore just return as it is.
     * 
     * 
     * Note this approach does not checks if val lies in the tree or not
     * It will give arithmetical successor of the val if val is not present in the tree
     */
    private static Integer perform(Node<Integer> root, Integer val) {
        if (root == null) {
            return -1;
        }

        if (root.data <= val) {
            return perform(root.right, val);
        } else {
            int left = perform(root.left, val);
            return (left == -1) ? root.data : left;
        }
    }
}