import java.util.Scanner;

public class ReverseInKGroups {

    // file:///Users/chesin/Downloads/Telegram%20Desktop/Ace%20the%20Java%20Coding%20Interview/2.%20Data%20Structures/3.%20Linked%20List/32.%20Reverse%20Alternative%20K%20Node%20in%20a%20Singly%20Linked%20List.html

    // Reverse in group of k
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();

        while (t != 0) {
            int n = scanner.nextInt();
            SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
            for (int i=0; i<n; i++) {
                SinglyNode<Integer> node = new SinglyNode<Integer>(scanner.nextInt());
                list.addTail(node);
            }
            list.printList();
            int k = scanner.nextInt();
            reverseAlternateKNodes(list, k);
            list.printList();
            t -= 1;
        }
        scanner.close();
    }

    private static void reverseAlternateKNodes(SinglyLinkedList<Integer> list, int k) {

        if (list.isEmpty() || k < 1) {
            return;
        }
        if (k >= list.getSize()) {
            // if k >= size, reverse the list
            list.reverseList();
            return;
        }
        SinglyNode<Integer> curHead, previous, current, next, prevTail;
        current = list.getHead(); // current keeps track of node to perform operation from
        curHead = list.getHead(); // curHead stores head node of the current group (the group over which operation is going on)
        previous = null; // previous keeps track of node to perform operation on
        prevTail = null; // prevTail stores tail node of previous group (the node from which we need to point to currentTail after reversing current group)
        boolean isFirst = true; // used to assign head node first group
        while (current != null) {
            int tempK = k;

            // Reversal of current group:
            while (tempK-- != 0 && current != null) {
                // till k is exhausted or group gets over, reverse the group
                next = current.getNextNode();
                current.setNextNode(previous);
                previous = current;
                current = next;
            }


            // Head and tail pointer manipulation after reversal:
            // after reversal of the current group, current will point to head of next group
            // therefore, we need to make curHead (head of current group) point to head of next group
            curHead.setNextNode(current);
            if (prevTail != null) {
                // if prevTail not null (meaning not the first group case where prevTail will be null), as there is no previous group
                // after reversal of current group, previous will point to "tail" (before reversal) or "new head" (after reversal) of current group 
                // therefore, we need to make tail of previous group point to new head of current group
                prevTail.setNextNode(previous);
            }
            if (isFirst) {
                // for the first group case, make head to previous which points new head of the current group
                list.setHead(previous);
                isFirst = false;
            }

            // Updates for next group operations:
            // make previous point to current head (head before reversal), which will be tail after reversal to carry on with reversal for next group
            previous = curHead;
            // similarly update prevTail to current tail for next group
            prevTail = previous;
            // update curHead to head of next group
            curHead = current;
        }
    }
}


/*
 * Similar LC Question: 
 * https://leetcode.com/problems/reverse-nodes-in-k-group/submissions/
 * 
 * Soln: (here, need to reverse group only when nodes in the group is more than equal to k)
 * class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k < 1) {
            return head;
        }
        ListNode curHead, previous, current, next, prevTail;
        current = head;
        curHead = head;
        previous = null;
        prevTail = null;
        boolean isFirst = true;

        int size = 0;
        while (current != null) {
            size += 1;
            current = current.next;
        }
        int completed = 0;
        current = head;

        while (current != null && (size - completed) >= k) {
            int tempK = k;
            while (tempK-- != 0 && current != null) {
                next = current.next;
                current.next = previous;
                previous = current;
                current = next;
                completed += 1;
            }
            curHead.next = current;
            if (prevTail != null) {
                prevTail.next = previous;
            }
            if (isFirst) {
                head = previous;
                isFirst = false;
            }
            previous = curHead;
            prevTail = previous;
            curHead = current;
        }
        return head;
    }
}
 */