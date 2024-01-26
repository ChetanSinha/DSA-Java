import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class GenerateBinaryStringUsingQueue {
    // file:///Users/chesin/Downloads/Telegram%20Desktop/Ace%20the%20Java%20Coding%20Interview/2.%20Data%20Structures/5.%20Stacks%20and%20Queues/7.%20Solution%20Review%3A%20Generate%20Binary%20Numbers%20from%201%20to%20n%20using%20Queue.html
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();

        while (t != 0) {
            int n = scanner.nextInt();
            func(n);
            t -= 1;
        }
        scanner.close();
    }

    private static void func(int n) {
        Queue<String> queue = new LinkedList<>();
        queue.add("1");
        while (n != 0) {
            String binaryString = queue.poll();
            System.out.println(binaryString);
            queue.add(binaryString + "0");
            queue.add(binaryString + "1");
            n -= 1;
        }
    }
}