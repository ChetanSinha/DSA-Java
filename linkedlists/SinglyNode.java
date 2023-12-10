public class SinglyNode<T> {
    private T data;
    private SinglyNode<T> nextNode;

    public SinglyNode(T value) {
        data = value;
        nextNode = null;
    }

    public T getData() {
        return data;
    }

    public SinglyNode<T> getNextNode() {
        return nextNode;
    }

    public void setData(T value) {
        data = value;
    }

    public void setNextNode(SinglyNode<T> node) {
        nextNode = node;
    }
}
