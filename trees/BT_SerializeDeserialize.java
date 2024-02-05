package trees;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

// https://leetcode.com/problems/serialize-and-deserialize-binary-tree/submissions/1157809167/

// file:///Users/chesin/Downloads/Telegram%20Desktop/Ace%20the%20Java%20Coding%20Interview/2.%20Data%20Structures/6.%20Trees/14.%20Serialize-Deserialize%20Binary%20Tree.html

public class BT_SerializeDeserialize {
    /**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

    

    private String levelOrder(TreeNode root) {
        if (root == null) {
            return "";
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        String str = "";
        while (!queue.isEmpty()) {
            TreeNode frontNode = queue.poll();
            str += (frontNode == null) ? "null" : frontNode.val;
            str += "\n";
            if (frontNode != null) {
                queue.add(frontNode.left);
                queue.add(frontNode.right);
            }
        }
        return str;
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        String str = levelOrder(root);
        // System.out.println(str);
        return str;
    }

    private TreeNode createNode(int val) {
        TreeNode node = new TreeNode();
        node.val = val;
        node.left = null;
        node.right = null;
        return node;
    }

    private TreeNode createBT(String[] nodes) {
        if (nodes.length == 0 || Objects.equals(nodes[0], "null") || Objects.equals(nodes[0], "")) {
            return null;
        }

        Queue<TreeNode> q = new LinkedList<>();
        int idx = 0;
        TreeNode root = createNode(Integer.parseInt(nodes[idx]));
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode front = q.poll();
            idx += 1;
            if (!Objects.equals(nodes[idx], "null") && !Objects.equals(nodes[idx], "")) {
                TreeNode left = createNode(Integer.parseInt(nodes[idx]));
                front.left = left;
                q.add(left);
            }

            idx += 1;
            if (!Objects.equals(nodes[idx], "null") && !Objects.equals(nodes[idx], "")) {
                TreeNode right = createNode(Integer.parseInt(nodes[idx]));
                front.right = right;
                q.add(right);
            }
        }

        return root;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] nodes = data.split("\n");
        // for (int i=0; i<nodes.length; i++) {
        //     System.out.println(nodes[i]);
        // }
        return createBT(nodes);
    }
}
