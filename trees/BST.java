package trees;

public class BST extends BinaryTree<Integer> {
    public void insertBST(Integer val) {
        this.root = insertRecursive(root, val);
    }

    private Node<Integer> insertRecursive(Node<Integer> root, int val) {
        if (root == null) {
            Node<Integer> node = new Node<>(val);
            root = node;
            return root;
        }

        if (root.data > val) {
            root.left = insertRecursive(root.left, val);
        } else {
            root.right = insertRecursive(root.right, val);
        }
        return root;
    }
}
