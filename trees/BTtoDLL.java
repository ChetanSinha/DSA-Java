package trees;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// file:///Users/chesin/Downloads/Telegram%20Desktop/Ace%20the%20Java%20Coding%20Interview/2.%20Data%20Structures/6.%20Trees/10.%20Convert%20Binary%20Tree%20to%20Doubly%20Linked%20List.html

// Convert binary tree to doubly linked list
public class BTtoDLL {
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
            BTtoDLL(tree);
            BTtoDLL2(tree);
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
     * This approach does not converts the tree into DLL in place ie tree itself is not converted
     * to DLL, rather new nodes are created for DLL
     * 
     * 
     * rule is simple:
     *  - whatever comming from left, go to last node of it insert root there
     *  - whatever comming from right, go to first node of it insert root there
     */
    private static void BTtoDLL(BinaryTree<Integer> tree) {
        DoublyNode<Integer> head = func(tree.root);
        printList(getFirstNode(head));
    }

    private static DoublyNode<Integer> func(Node<Integer> root) {
        if (root == null) {
            return null;
        }

        DoublyNode<Integer> left = func(root.left);
        DoublyNode<Integer> right = func(root.right);

        
        DoublyNode<Integer> leftLast = getLastNode(left);
        DoublyNode<Integer> rightFirst = getFirstNode(right);

        DoublyNode<Integer> node = new DoublyNode<Integer>(root.data);
        node.setPrevNode(leftLast);
        if (leftLast != null) {
            leftLast.setNextNode(node);
        }
        
        node.setNextNode(rightFirst);
        if (rightFirst != null) {
            rightFirst.setPrevNode(node);
        }
        return node;
    }

    private static DoublyNode<Integer> getLastNode(DoublyNode<Integer> node) {
        if (node == null) {
            return null;
        }
        DoublyNode<Integer> last = node;
        while (last.getNextNode() != null) {
            last = last.getNextNode();
        }
        return last;
    }

    private static DoublyNode<Integer> getFirstNode(DoublyNode<Integer> node) {
        if (node == null) {
            return null;
        }
        DoublyNode<Integer> first = node;
        while (first.getPrevNode() != null) {
            first = first.getPrevNode();
        }
        return first;
    }

    public static void printList(DoublyNode<Integer> headNode) {
        DoublyNode<Integer> pointer = headNode;
        while (pointer != null) {
            System.out.print(pointer.getData() + "<->");
            pointer = pointer.getNextNode();
        }
        System.out.println("NULL");
    }


    /*
     * This approach changes/updates tree to DLL
     * new nodes are not created.
     * left pointer becomes previous pointer of DLL
     * right pointer becomes next pointer of DLL
     * 
     * rule is simple:
     *  - maintain head and tail of DLL
     *  - as we traverse through tree, if tail is yet null, then there is no node in DLL, therefore, make this node as head
     *  - update tail with all the next nodes during traverse
     */

    static Node<Integer> head;
    static Node<Integer> tail;
    private static void BTtoDLL2(BinaryTree<Integer> tree) {
        head = null;
        tail = null;
        func1(tree.root);
        printList(head);
    }

    private static void func1(Node<Integer> root) {
        if (root == null) {
            return;
        }
        func1(root.left);

        // DLL updates
        if (tail == null) {
            head = root;
        } else {
            root.left = tail;
            tail.right = root;
        }  
        tail = root;
        // 
        
        func1(root.right);
    }

    private static void printList(Node<Integer> head) {
        Node<Integer> temp = head;
        while (temp != null) {
            System.out.print(temp.data + "<->");
            temp = temp.right;
        }
        System.out.println("NULL");
    }
}