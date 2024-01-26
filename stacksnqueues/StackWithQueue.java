import java.util.LinkedList;
import java.util.Queue;

/**
 * StackWithQueue
 */
public class StackWithQueue<T> {

    Queue<T> queue1 = new LinkedList<>();
    Queue<T> queue2 = new LinkedList<>();


    // Push o(1), pop o(n)
    public void push(T val) {
        queue1.add(val);
    }



    // Push o(n), pop o(1)
    public void push1(T val) {
        while (queue1.size() > 0) {
            queue2.add(queue1.poll());
        }
        queue1.add(val);
        while (queue2.size() > 0) {
            queue1.add(queue2.poll());
        }
    }

    public Integer size() {
        return queue1.size() + queue2.size();
    }

    public boolean isEmpty() {
        return queue1 == null || queue2 == null || this.size() <= 0;
    }

    public T top() {
        if (this.isEmpty()) {
            return null;
        }

        while (queue1.size() > 1) {
            queue2.add(queue1.poll());
        }
        T val = queue1.peek();
        queue2.add(queue1.poll());

        Queue<T> temp = queue1;
        this.queue1 = queue2;
        this.queue2 = temp;
        return val;
    }

    // No temp array used
    public T top1() {
        if (this.isEmpty()) {
            return null;
        }
        return queue1.peek();
    }

    public T pop() {
        if (this.isEmpty()) {
            return null;
        }

        while (queue1.size() > 1) {
            queue2.add(queue1.poll());
        }
        T val = queue1.poll();

        Queue<T> temp = queue1;
        this.queue1 = queue2;
        this.queue2 = temp;
        return val;
    }

    // No temp array used
    public T pop1() {
        if (this.isEmpty()) {
            return null;
        }
        return queue1.poll();
    }
}