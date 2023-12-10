public class SinglyLinkedList<T> {

    private SinglyNode<T> headNode;
    private int size;

    public SinglyLinkedList() {
        headNode = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public SinglyNode<T> getHead() {
        return headNode;
    }

    public void setHead(SinglyNode<T> node) {
        headNode = node;
    }

    public boolean isEmpty() {
        return (headNode == null);
    }

    public void addHead(SinglyNode<T> node) {
        if (node == null) {
            return;
        }
        node.setNextNode(headNode);
        headNode = node;     
        size += 1;
    }

    public void addTail(SinglyNode<T> node) {
        if (node == null) {
            return;
        }
        if (headNode == null) {
            addHead(node);
            return;
        }
        SinglyNode<T> pointer = headNode;
        while (pointer.getNextNode() != null) {
            pointer = pointer.getNextNode();
        }
        pointer.setNextNode(node);
        size += 1;
    }

    public void printList() {
        SinglyNode<T> pointer = headNode;
        while (pointer != null) {
            System.out.print(pointer.getData() + "->");
            pointer = pointer.getNextNode();
        }
        System.out.println("NULL");
    }

    public boolean isValuePresent(T value) {
        SinglyNode<T> pointer = headNode;
        while (pointer != null) {
            if (pointer.getData().equals(value)) {
                return true;
            }
            pointer = pointer.getNextNode();
        }
        return false;
    }

    public void deleteHead() {
        if (headNode == null) {
            return;
        }
        SinglyNode<T> pointer = headNode;
        headNode = headNode.getNextNode();
        pointer.setNextNode(null);
        pointer = null;
    }

    public void deleteTail() {
        if (headNode == null || size < 1) {
            return;
        }
        if (size == 1) {
            headNode = null;
            return;
        }
        SinglyNode<T> pointer = headNode;
        while (pointer.getNextNode().getNextNode() != null) {
            pointer = pointer.getNextNode();
        }
        pointer.setNextNode(null);
    }

    public void deleteFirstValue(T value) {
        if (headNode == null || size < 1) {
            return;
        }
        if (size == 1) {
            if (headNode.getData().equals(value)) {
                headNode = null;
            }
            return;
        }
        SinglyNode<T> pointer = headNode;
        while (!pointer.getNextNode().getData().equals(value)) {
            pointer = pointer.getNextNode();
            if (pointer.getNextNode() == null) {
                return;
            }
        }
        SinglyNode<T> tempPointer = pointer.getNextNode();
        pointer.setNextNode(tempPointer.getNextNode());
        tempPointer.setNextNode(null);
        tempPointer = null;
    }

    public void reverseList() {
        if (headNode == null || size == 1) {
            return;
        }
        SinglyNode<T> previous = null;
        SinglyNode<T> current = headNode;
        SinglyNode<T> next = null;
        while (current != null) {
            next = current.getNextNode();
            current.setNextNode(previous);
            previous = current;
            current = next;
        }
        headNode = previous;
    }

    public SinglyNode<T> findMiddleNode() {
        if (headNode == null || size == 1) {
            return headNode;
        }
        SinglyNode<T> fast = headNode;
        SinglyNode<T> slow = headNode;
        while (slow != null && fast != null && fast.getNextNode() != null) {
            fast = fast.getNextNode().getNextNode();
            if (fast != null) {
                // handles case for even length
                slow = slow.getNextNode();
            }
        }
        return slow;
    }

    public void rotateByK(int k) {
        if (headNode == null) {
            return;
        }
        k = k % size;
        SinglyNode<T> right = headNode;
        while (k-- != 0 && right != null && right.getNextNode() != null) {
            right = right.getNextNode();
        }
        SinglyNode<T> left = headNode;
        while (right.getNextNode() != null) {
            right = right.getNextNode();
            left = left.getNextNode();
        }
        right.setNextNode(headNode);
        headNode = left.getNextNode();
        left.setNextNode(null);
    }
}