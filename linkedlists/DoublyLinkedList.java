public class DoublyLinkedList<T> {
    
    private DoublyNode<T> headNode;
    private int size;

    public DoublyLinkedList() {
        headNode = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public DoublyNode<T> getHead() {
        return headNode;
    }

    public boolean isEmpty() {
        return (headNode == null);
    }

    public void addHead(DoublyNode<T> node) {
        if (node == null) {
            return;
        }
        if (headNode != null) {
            headNode.setPrevNode(node);
        }
        node.setNextNode(headNode);
        headNode = node;     
        size += 1;
    }

    public void addTail(DoublyNode<T> node) {
        if (node == null) {
            return;
        }
        if (headNode == null) {
            addHead(node);
            return;
        }
        DoublyNode<T> pointer = headNode;
        while (pointer.getNextNode() != null) {
            pointer = pointer.getNextNode();
        }
        pointer.setNextNode(node);
        node.setPrevNode(pointer);
        size += 1;
    }

    public void printList() {
        DoublyNode<T> pointer = headNode;
        while (pointer != null) {
            System.out.print(pointer.getData() + "<->");
            pointer = pointer.getNextNode();
        }
        System.out.println("NULL");
    }

    public boolean isValuePresent(T value) {
        DoublyNode<T> pointer = headNode;
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
        DoublyNode<T> pointer = headNode;
        headNode = headNode.getNextNode();
        headNode.setPrevNode(null);
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
        DoublyNode<T> pointer = headNode;
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
        DoublyNode<T> pointer = headNode;
        while (!pointer.getNextNode().getData().equals(value)) {
            pointer = pointer.getNextNode();
            if (pointer.getNextNode() == null) {
                return;
            }
        }
        DoublyNode<T> tempPointer = pointer.getNextNode();
        pointer.setNextNode(tempPointer.getNextNode());
        if (tempPointer.getNextNode() != null) {
            tempPointer.getNextNode().setPrevNode(pointer);
        }
        tempPointer.setNextNode(null);
        tempPointer.setPrevNode(null);
        tempPointer = null;
    }

    public void reverseList() {
        if (headNode == null || size == 1) {
            return;
        }
        DoublyNode<T> previous = null;
        DoublyNode<T> current = headNode;
        DoublyNode<T> next = null;
        while (current != null) {
            next = current.getNextNode();
            previous = current.getPrevNode();
            current.setNextNode(previous);
            current.setPrevNode(next);
            previous = current;
            current = next;
        }
        headNode = previous;
    }
}
