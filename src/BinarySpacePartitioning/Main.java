package BinarySpacePartitioning;

import Utils.SimpleImage;

import java.awt.*;
import java.util.LinkedList;
import java.util.Set;

public class Main {

  private static final int HEIGHT = 1000;
  private static final int WIDTH = 1000;

  public static void main(String[] args) {
    // Generate Our Tree Performing Splits for Each Iteration
    TreeNode<Container> root = generateTree(100);
    Set<TreeNode<Container>> leafNodes = root.getLeafNodes();

    // Convert The Rooms Into a 2D Array Representation
    String[][] arr = new String[WIDTH + 1][HEIGHT + 1];

    // set all characters in array
    for (int i = 0; i < WIDTH + 1; i++) {
      for (int j = 0; j < HEIGHT + 1; j++) {
        arr[i][j] = Utils.OPEN;
      }
    }

    // Fill in Board
    for (TreeNode<Container> treeNode : leafNodes) {
      treeNode.getData().fillArrayByContainer(arr);
    }

    // Print Board and Node Tree
    printBoard(arr);
    root.printTree();

    // Save Image
    saveImage(arr);
  }

  private static TreeNode<Container> generateTree(int iterations) {
    TreeNode<Container> root = new TreeNode<>(null, new Container(0, WIDTH, HEIGHT, 0));

    // BinarySpacePartitioning.Generate the tree
    LinkedList<TreeNode<Container>> queue = new LinkedList<>();
    queue.add(root);
    for (int i = 0; i < iterations; i++) {
      TreeNode<Container> current = queue.removeFirst();
      Container.splitContainer(current);
      queue.add(current.getLeft());
      queue.add(current.getRight());
    }
    return root;
  }

  private static void printBoard(String[][] arr) {
    for (String[] strings : arr) {
      for (String string : strings) {
        System.out.print(string);
      }
      System.out.println();
    }
  }

  private static void saveImage(String[][] arr) {
    SimpleImage image = new SimpleImage(WIDTH + 1, HEIGHT + 1);
    for (int i = 0; i <= WIDTH; i++) {
      for (int j = 0; j <= HEIGHT; j++) {
        if (arr[i][j].equals(Utils.OPEN)) {
          image.set(i, j, Color.black);
        } else {
          image.set(i, j, Color.green);
        }
      }
    }

    image.save("current.png");
  }
}
