public class DoublyNode<T> {
    private T data;
    private DoublyNode<T> prevNode;
    private DoublyNode<T> nextNode;

    public DoublyNode(T value) {
        data = value;
        prevNode = null;
        nextNode = null;
    }

    public T getData() {
        return data;
    }

    public DoublyNode<T> getNextNode() {
        return nextNode;
    }

    public DoublyNode<T> getPrevNode() {
        return prevNode;
    }

    public void setData(T value) {
        data = value;
    }

    public void setNextNode(DoublyNode<T> node) {
        nextNode = node;
    }

    public void setPrevNode(DoublyNode<T> node) {
        prevNode = node;
    }
}
