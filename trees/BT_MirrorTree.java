package trees;


// https://leetcode.com/problems/invert-binary-tree/
// file:///Users/chesin/Downloads/Telegram%20Desktop/Ace%20the%20Java%20Coding%20Interview/2.%20Data%20Structures/6.%20Trees/16.%20Mirror%20Binary%20Tree%20Nodes.html

public class BT_MirrorTree {
    private TreeNode func(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode left = func(root.left);
        TreeNode right = func(root.right);

        root.right = left;
        root.left = right;
        return root;
    }

    public TreeNode invertTree(TreeNode root) {
        return func(root);    
    }
}
