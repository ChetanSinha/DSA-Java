package trees;


import java.util.Scanner;


public class JavaRough {
    static class NaryTreeNode {
        int val;
        NaryTreeNode[] child;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();

        while (t != 0) {
            NaryTreeNode root = createNaryNode(1);
            NaryTreeNode child1 = createNaryNode(2);
            NaryTreeNode child2 = createNaryNode(3);
            NaryTreeNode child21 = createNaryNode(5);
            NaryTreeNode child22 = createNaryNode(6);
            NaryTreeNode child3 = createNaryNode(4);

            child2.child = new NaryTreeNode[2];
            child2.child[0] = child21;
            child2.child[1] = child22;

            root.child = new NaryTreeNode[3];
            root.child[0] = child1;
            root.child[1] = child2;
            root.child[2] = child3;

            TreeNode bt = convertNaryToBT(root);
            int height = getHeight(bt);
            for (int i=1; i<= height; i++) {
                printLevelOrder(bt, i);
                System.out.println();
            }
            t -= 1;
        }
        scanner.close();
    }

    private static NaryTreeNode createNaryNode(int val) {
        NaryTreeNode node = new NaryTreeNode();
        node.val = val;
        node.child = new NaryTreeNode[0];
        return node;
    }
    
    private static TreeNode convertNaryToBT(NaryTreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode prev = null;
        TreeNode head = null;
        for (int i=0; i<root.child.length; i++) {
            TreeNode node = convertNaryToBT(root.child[i]);
            if (prev == null) {
                prev = node;
                head = node;
            } else {
                if (prev.left == null) {
                    prev.left = node;
                } else {
                    prev.right = node;
                }
                prev = node;
            }
        }
        TreeNode btRoot = new TreeNode();
        btRoot.val = root.val;
        btRoot.left = head;
        return btRoot;
    }

    private static void printLevelOrder(TreeNode root, int level) {
        if (root == null) {
            return;
        }

        if (level == 1) {
            System.out.print(root.val + " ");
        } else if (level > 1) {
            printLevelOrder(root.left, level-1);
            printLevelOrder(root.right, level-1);
        }
    }

    public static int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getHeight(root.left);
        int right = getHeight(root.right);

        return Math.max(left, right) + 1;
    }
}