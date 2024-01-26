package trees;

public class Node<T> {
    T data;
    Node<T> left, right;

    public Node() {
        this.data = null;
        this.left = null;
        this.right = null;
    }

    public Node(T val) {
        this.data = val;
        this.left = null;
        this.right = null;
    }
}
