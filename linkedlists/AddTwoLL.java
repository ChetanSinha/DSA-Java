import java.util.Scanner;

public class AddTwoLL {
    // file:///Users/chesin/Downloads/Telegram%20Desktop/Ace%20the%20Java%20Coding%20Interview/2.%20Data%20Structures/3.%20Linked%20List/33.%20Add%20Two%20Integers%20Represented%20by%20Linked%20Lists.html
    
    // Add two LL
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();

        while (t != 0) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            SinglyLinkedList<Integer> l1 = new SinglyLinkedList<>();
            SinglyLinkedList<Integer> l2 = new SinglyLinkedList<>();
            for (int i=0; i<n; i++) {
                SinglyNode<Integer> node = new SinglyNode<Integer>(scanner.nextInt());
                l1.addTail(node);
            }
            l1.printList();

            for (int i=0; i<m; i++) {
                SinglyNode<Integer> node = new SinglyNode<Integer>(scanner.nextInt());
                l2.addTail(node);
            }
            l2.printList();

            SinglyLinkedList<Integer> result = addList(l1, l2);
            result.printList();
            t -= 1;
        }
        scanner.close();
    }

    /*
     * 
        7 3 2
        1 0 9 9
        result: 8->3->1->0->1->NULL
     */
    private static SinglyLinkedList<Integer> addList(SinglyLinkedList<Integer> l1, SinglyLinkedList<Integer> l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        SinglyLinkedList<Integer> result = new SinglyLinkedList<>();
        SinglyNode<Integer> p1, p2;
        p1 = l1.getHead();
        p2 = l2.getHead();

        int sum = 0;
        int remainder = 0;

        while (p1 != null && p2 != null) {
            sum = p1.getData() + p2.getData() + remainder;
            remainder = sum / 10;
            sum = sum % 10;
            SinglyNode<Integer> node = new SinglyNode<Integer>(sum);
            result.addTail(node);
            p1 = p1.getNextNode();
            p2 = p2.getNextNode();
        }

        while (p1 != null) {
            sum = p1.getData() + remainder;
            remainder = sum / 10;
            sum = sum % 10;
            SinglyNode<Integer> node = new SinglyNode<Integer>(sum);
            result.addTail(node);
            p1 = p1.getNextNode();
        }

        while (p2 != null) {
            sum = p2.getData() + remainder;
            remainder = sum / 10;
            sum = sum % 10;
            SinglyNode<Integer> node = new SinglyNode<Integer>(sum);
            result.addTail(node);
            p2 = p2.getNextNode();
        }

        if (remainder > 0) {
            SinglyNode<Integer> node = new SinglyNode<Integer>(remainder);
            result.addTail(node);
        }

        return result;
    }
    
}
