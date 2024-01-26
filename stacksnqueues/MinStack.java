import java.util.Stack;

public class MinStack {

    // Each node in the stack will have two things, current value and min till current point
    private class Node {
        int val;
        int min;
            
        private Node(int val, int min) {
            this.val = val;
            this.min = min;
        }
    }

    Stack<Node> stack = new Stack<>();
    
    public void push(int val) {
        if (stack.empty()) {
            stack.add(new Node(val, val));
        } else {
            Node top = stack.peek();
            stack.add(new Node(val, Math.min(top.min, val)));
        }
    }
    
    public void pop() {
        if (stack.empty()) {
            return;
        }
        stack.pop();
    }
    
    public int top() {
        if (stack.empty()) {
            return -1;
        }
        return stack.peek().val;
    }
    
    public int getMin() {
        if (stack.empty()) {
            return -100000;
        }
        return stack.peek().min;
    }
}
