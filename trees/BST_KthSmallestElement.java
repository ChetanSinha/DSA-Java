package trees;

import java.util.PriorityQueue;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
}


/*
 * Follow up: If the BST is modified often (i.e., we can do insert and delete operations) 
 * and you need to find the kth smallest frequently, how would you optimize?
 * 
 * 
 * - we can use min heap of size k to get kth smallest in O(1) 
 */

// https://leetcode.com/problems/kth-smallest-element-in-a-bst/
public class BST_KthSmallestElement {



    // W/o extra space, using BST property of giving sorted number in inorder traversal
    // therefore we can count till k = 0
    int K;
    int ans;

    private void func(TreeNode root) {
        if (root == null) {
            return;
        }
        func(root.left);
        K -= 1;
        if (K == 0) {
            ans = root.val;
        }
        func(root.right);
    }

    public int kthSmallest(TreeNode root, int k) {
        K = k;
        ans = 0;
        func(root);
        return ans;
    }



    // Using priority queue - remove k elements
    // we can also use priority queue of size k to optimize k
    PriorityQueue<Integer> pq;

    private void func1(TreeNode root) {
        if (root == null) {
            return;
        }
        func(root.left);
        pq.add(root.val);
        func(root.right);
    }

    public int kthSmallest1(TreeNode root, int k) {
        pq = new PriorityQueue<>();
        func1(root);

        int ans = 0;
        while (k > 0) {
            ans = pq.poll();
            k -= 1;
        }

        return ans;
    }
}
