import java.util.Scanner;
import java.util.Stack;

public class IdentifyCelebrity {
    // file:///Users/chesin/Downloads/Telegram%20Desktop/Ace%20the%20Java%20Coding%20Interview/2.%20Data%20Structures/5.%20Stacks%20and%20Queues/20.%20Challenge%208%3A%20Solve%20a%20Celebrity%20Problem%20using%20a%20Stack.html
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while (t != 0) {
            int n = scanner.nextInt();
            int[][] nums1 = new int[n][n];
            
            for (int i=0; i<n; i++) {
                for (int j=0; j<n; j++) {
                    nums1[i][j] = scanner.nextInt();
                }
            }

            int ans = func(nums1);
            System.out.println(ans);
            t -= 1;
        }
        scanner.close();
    }


    /*
     * insert every person in stack
     * for each of top 2, check if they follow each other or not
     * remove the one who follows
     * 
     * at last when item will remain in stack, check if all other folks follow him or not
     */

    private static int func(int[][] nums1) {
        Stack<Integer> peopleStack = new Stack<>();

        for (int i=0; i<nums1.length; i++) {
            peopleStack.add(i);
        }

        while (peopleStack.size() != 1) {
            int person1 = peopleStack.pop();
            int person2 = peopleStack.pop();
            if (nums1[person1][person2] == 1 && nums1[person2][person1] == 1) {
                continue;
            } else if (nums1[person1][person2] == 1) {
                peopleStack.add(person2);
            } else if (nums1[person2][person1] == 1) {
                peopleStack.add(person1);
            }
        }

        int couldBeCelebrity = peopleStack.pop();
        for (int i=0; i<nums1.length; i++) {
            if (i != couldBeCelebrity && nums1[i][couldBeCelebrity] != 1) {
                return -1;
            }
        }
        return couldBeCelebrity;
    }
}