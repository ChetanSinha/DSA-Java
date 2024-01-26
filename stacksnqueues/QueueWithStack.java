import java.util.Stack;

/**
 * QueueWithStack with fast enqueue
 */
public class QueueWithStack<T> {

    Stack<T> stack1 = new Stack<>();
    Stack<T> stack2 = new Stack<>();

    public void enqueue(T val) {
        stack1.push(val);
    }

    public Integer size() {
        return stack1.size() + stack2.size();
    }

    public boolean isEmpty() {
        return stack1 == null || stack2 == null || this.size() <= 0;
    }

    public T peek() {
        if (this.isEmpty()) {
            return null;
        }

        if (stack2.size() > 0) {
            return stack2.peek();
        }

        while (stack1.size() > 0) {
            stack2.push(stack1.pop());
        }
        return stack2.peek();
    }

    public T dequeue() {
        if (this.isEmpty()) {
            return null;
        }

        if (stack2.size() > 0) {
            return stack2.pop();
        }

        while (stack1.size() > 0) {
            stack2.push(stack1.pop());
        }
        return stack2.pop();
    }
}