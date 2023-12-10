import java.util.Random;
import java.util.Scanner;

public class FloydCycleAlgo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();

        while (t != 0) {
            int n = scanner.nextInt();
            SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
            for (int i=0; i<n; i++) {
                SinglyNode<Integer> node = new SinglyNode<>(scanner.nextInt());
                list.addTail(node);
            }
            list.printList();
            detectLoop(list);
            addRandomLoop(list);
            detectLoop(list);
            findLoopPoint(list);
            t -= 1;
        }
        scanner.close();
    }

    private static void addRandomLoop(SinglyLinkedList<Integer> list) {
        SinglyNode<Integer> pointer = list.getHead();
        while (pointer.getNextNode() != null) {
            pointer = pointer.getNextNode();
        }
        Random random = new Random();
        Integer raInteger = random.nextInt(list.getSize());;
        SinglyNode<Integer> tempPointer = list.getHead();
        while ((raInteger--) != 0 && tempPointer.getNextNode() != null) {
            tempPointer = tempPointer.getNextNode();
        }
        System.out.println("Added loop at: " + tempPointer.getData());
        pointer.setNextNode(tempPointer);
    }

    private static void detectLoop(SinglyLinkedList<Integer> list) {
        // Floyd cycle detection - o(n)
        // fast, slow pointer
        SinglyNode<Integer> slow = list.getHead();
        SinglyNode<Integer> fast = list.getHead();

        while (slow != null && fast != null && fast.getNextNode() != null) {
            slow = slow.getNextNode();
            fast = fast.getNextNode().getNextNode();
            if (slow == fast) {
                // if fast slow pointer meets
                System.out.println("Loop detected");
                return;
            }
        }
        System.out.println("No loop present");
    }

    private static void findLoopPoint(SinglyLinkedList<Integer> list) {
        SinglyNode<Integer> slow = list.getHead();
        SinglyNode<Integer> fast = list.getHead();
        while (slow != null && fast != null && fast.getNextNode() != null) {
            slow = slow.getNextNode();
            fast = fast.getNextNode().getNextNode();
            if (slow == fast) {
                break;
            }
        }
        if (slow != fast) {
            System.out.println("No loop present");
            return;
        }
        // After checking loop exists with slow = fast
        // to find node at which loop occurs, make slow at head and increment both till both get same.
        slow = list.getHead();
        while (slow != fast) {
            slow = slow.getNextNode();
            fast = fast.getNextNode();
        }
        System.out.println("Loop point at node: " + slow.getData());
    }
}