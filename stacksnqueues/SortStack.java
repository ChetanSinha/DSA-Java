import java.util.Scanner;
import java.util.Stack;

public class SortStack {
    // file:///Users/chesin/Downloads/Telegram%20Desktop/Ace%20the%20Java%20Coding%20Interview/2.%20Data%20Structures/5.%20Stacks%20and%20Queues/15.%20Solution%20Review%3A%20Sort%20the%20Values%20in%20a%20Stack.html
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();

        while (t != 0) {
            int n = scanner.nextInt();
            Stack<Integer> stack = new Stack<>();
            for (int i=0; i<n; i++) {
                stack.push(scanner.nextInt());
            }
            Stack<Integer> res =  func(stack);
            System.out.println(res);
            t -= 1;
        }
        scanner.close();
    }

    private static Stack<Integer> func(Stack<Integer> stack) {
        if (stack.empty()) {
            return stack;
        }

        Stack<Integer> ansStack = new Stack<>();
        while (!stack.empty()) {
            Integer toBeInserted = stack.pop();
            // pop from ans stack till appropriate position is not found for number to be inserted.
            while (!ansStack.empty() && ansStack.peek() > toBeInserted) {
                stack.push(ansStack.pop());
            }
            ansStack.push(toBeInserted);
        }

        return ansStack;
    }
}