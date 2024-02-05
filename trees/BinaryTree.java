package trees;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree<T> {
    Node<T> root;

    public BinaryTree() {
        root = null;
    }

    public BinaryTree(T val) {
        root = new Node<T>(val);
    }


    public void createTree(List<T> values) {
        if (values == null || values.isEmpty()) {
            return;
        }
        root = createBT(values);
    }

    private Node<T> createBT(List<T> nodes) {
        if (nodes.size() == 0 || Objects.equals(nodes.get(0), "null")) {
            return null;
        }
        Queue<Node<T>> q = new LinkedList<>();
        int idx = 0;
        Node<T> root = new Node<>(nodes.get(idx));
        q.add(root);
        while (!q.isEmpty()) {
            Node<T> front = q.poll();
            if (++idx < nodes.size() && !Objects.equals(nodes.get(idx), "null")) {
                Node<T> left = new Node<>(nodes.get(idx));
                front.left = left;
                q.add(left);
            }

            if (++idx < nodes.size() && !Objects.equals(nodes.get(idx), "null")) {
                Node<T> right = new Node<>(nodes.get(idx));
                front.right = right;
                q.add(right);
            }
        }
        return root;
    }

    public int getHeight(Node<T> root) {
        if (root == null) {
            return 0;
        }

        int left = getHeight(root.left);
        int right = getHeight(root.right);

        return Math.max(left, right) + 1;
    }


/*
 * =============================== INSERT LEVEL ORDER ======================================
 */

    public void insertLevelOrder(Node<T> node, boolean recursive) {
        if (recursive) {
            int height = getHeight(root);
            for (int i=height-1; i<=height; i++) {
                if (insertLevelOrderRecursive(root, node, i)) {
                    return;
                }
            }
            return;
        }
        insertLevelOrderIterative(node);
    }

    private void insertLevelOrderIterative(Node<T> node) {
        if (root == null) {
            root = node;
            return;
        }
        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node<T> frontNode = queue.poll();
            if (frontNode.left == null) {
                frontNode.left = node;
                return;
            }
            if (frontNode.right == null) {
                frontNode.right = node;
                return;
            }
            queue.add(frontNode.left);
            queue.add(frontNode.right);
        }
    }

    private boolean insertLevelOrderRecursive(Node<T> root, Node<T> node, int level) {
        if (root == null) {
            root = node;
            return true;
        }

        if (level == 1) {
            if (root.left == null) {
                root.left = node;
                return true;
            }

            if (root.right == null) {
                root.right = node;
                return true;
            }
        } else if (level > 1) {
            if (insertLevelOrderRecursive(root.left, node, level-1)) {
                return true;
            }
            return insertLevelOrderRecursive(root.right, node, level-1);
        }
        return false;
    }

/*
 *  ============================= LEVEL ORDER TRAVERSAL ===================================
 */
    public void printLevelOrder(boolean recursive) {
        if (recursive) {
            int height = getHeight(root);
            for (int i=1; i<= height; i++) {
                printLevelOrderRecursive(root, i);
                System.out.println();
            }
            return;
        }
        printLevelOrderIterative(root);
    }

    private void printLevelOrderRecursive(Node<T> root, int level) {
        if (root == null) {
            return;
        }

        if (level == 1) {
            System.out.print(root.data + " ");
        } else if (level > 1) {
            printLevelOrderRecursive(root.left, level-1);
            printLevelOrderRecursive(root.right, level-1);
        }
    }

    // Rule - depth search - stack ; breadth search - queue

    private void printLevelOrderIterative(Node<T> root) {
        if (root == null) {
            return;
        }


        // simple breadth search - queue
        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node<T> frontNode = queue.poll();
            System.out.print(frontNode.data + " ");
            if (frontNode.left != null) {
                queue.add(frontNode.left);
            }
            if (frontNode.right != null) {
                queue.add(frontNode.right);
            }
        }
        System.out.println();
    }

/*
=================================== IN ORDER TRAVERSAL ======================================
*/    
    public void printInOrder(boolean recursive) {
        if (recursive) {
            printInOrderRecursive(root);
            System.out.println();
            return;
        }
        printInOrderIterative(root);
    }

    private void printInOrderRecursive(Node<T> root) {
        if (root == null) {
            return;
        }
        printInOrderRecursive(root.left);
        System.out.print(root.data + " ");
        printInOrderRecursive(root.right);
    }

    private void printInOrderIterative(Node<T> root) {
        if (root == null) {
            return;
        }

        Stack<Node<T>> stack = new Stack<>();

        // depth traversal - stack
        // traverse till extreme left, till no more left is remaining
        // take items one by one, print and move to its right
        while (root != null || !stack.empty()) {
            while (root != null) {
                stack.add(root);
                root = root.left;
            }

            root = stack.pop();
            System.out.print(root.data + " ");
            root = root.right;
        }
        System.out.println();
    }

/*
 * 
 * =============================== PRE ORDER TRAVERSAL ====================================
 */

    public void printPreOrder(boolean recursive) {
        if (recursive) {
            preOrderTraversalRecursive(root);
            System.err.println();
            return;
        }
        preOrderTraversalIterative(root);
    }

    private void preOrderTraversalRecursive(Node<T> root) {
        if (root == null) {
            return;
        }

        System.out.print(root.data + " ");
        preOrderTraversalRecursive(root.left);
        preOrderTraversalRecursive(root.right);
    }

    private void preOrderTraversalIterative(Node<T> root) {
        if (root == null) {
            return;
        }

        Stack<Node<T>> stack = new Stack<>();

        // same as in order - except print root while pushing to stack
        while (root != null || !stack.empty()) {
            while (root != null) {
                System.out.print(root.data + " ");
                stack.add(root);
                root = root.left;
            }

            root = stack.pop();
            root = root.right;
        }
        System.out.println();
    }

/*
 * 
 * =============================== POST ORDER TRAVERSAL ====================================
 */

    public void printPostOrder(boolean recursive) {
        if (recursive) {
            postOrderTraversalRecursive(root);
            System.out.println();
            return;
        }
        postOrderTraversalIterative(root);
    }

    private void postOrderTraversalRecursive(Node<T> root) {
        if (root == null) {
            return;
        }

        postOrderTraversalRecursive(root.left);
        postOrderTraversalRecursive(root.right);
        System.out.print(root.data + " ");
    }

    private void postOrderTraversalIterative(Node<T> root) {
        if (root == null) {
            return;
        }

        Stack<Node<T>> stack = new Stack<>();


        // we need to ensure that left and right has been traversed before printing root
        // and also since we need to print left first, we go extreme left, with storing root and right
        // will print root only if either right is null or right has already been processed
        // how to check if right has already been processed??
        // by checking if stack element just after root is not same as right (as we are pushing root and right in order)
        while (root != null || !stack.empty()) {
            while (root != null) {
                if (root.right != null) {
                    stack.add(root.right);
                }
                stack.add(root);
                root = root.left;
            }

            root = stack.pop();
            Node<T> right = null;
            if (!stack.isEmpty() && root.right == stack.peek()) {
                stack.pop();
                right = root.right;
                stack.add(root);
            } else {
                System.out.print(root.data + " "); 
            }
            root = right;
        }

        System.out.println();
    }
}
