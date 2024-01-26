package trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TreeTraversal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();

        while (t != 0) {
            int n = scanner.nextInt();
            List<Integer> arr = new ArrayList<>();
            BinaryTree<Integer> tree = new BinaryTree<>();
            for (int i=0; i<n; i++) {
                arr.add(getNextNode(scanner));
            }
            tree.createTree(arr);
            iterativeTraversal(tree);
            recursiveTraversal(tree);
            t -= 1;
        }
        scanner.close();
    }

    public static Integer getNextNode(Scanner scanner) {
        String userInput = scanner.next();
        if (userInput.equalsIgnoreCase("null")) {
            return null;
        } else {
            int intValue = Integer.parseInt(userInput);
            return intValue;
        }
    }

    private static void iterativeTraversal(BinaryTree<Integer> tree) {
        System.out.println("=======================================");
        tree.printLevelOrder(false);
        System.out.println("=======================================");
        tree.printInOrder(false);
        System.out.println("=======================================");
        tree.printPreOrder(false);
        System.out.println("=======================================");
        tree.printPostOrder(false);
        System.out.println("=======================================");
    }

    private static void recursiveTraversal(BinaryTree<Integer> tree) {
        System.out.println("---------------------------------------");
        tree.printLevelOrder(true);
        System.out.println("---------------------------------------");
        tree.printInOrder(true);
        System.out.println("---------------------------------------");
        tree.printPreOrder(true);
        System.out.println("---------------------------------------");
        tree.printPostOrder(true);
        System.out.println("---------------------------------------");
    }
}