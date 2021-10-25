package Test;

import Generate.Container;
import Generate.TreeNode;

import java.awt.*;
import java.util.HashSet;
import java.util.LinkedList;

public class Main {

	public static void main(String[] args) {

		final int HEIGHT = 100;
		final int WIDTH = 100;

		TreeNode<Container> root = new TreeNode<>(null, new Container(0, WIDTH, HEIGHT, 0));
		int iterations = 5;

		// Generate the tree
		LinkedList<TreeNode<Container>> queue = new LinkedList<>();
		queue.add(root);
		for (int i = 0; i < iterations; i++) {
			TreeNode<Container> current = queue.removeFirst();
			Container.splitContainer(current);
			queue.add(current.getLeft());
			queue.add(current.getRight());
		}

		// Visit all nodes, cataloging leaf nodes
		LinkedList<TreeNode<Container>> search = new LinkedList<>();
		HashSet<TreeNode<Container>> leafNodes = new HashSet<>();

		search.add(root);

		while (!search.isEmpty()) {
			TreeNode<Container> current = search.removeFirst();

			if (current.hasChildren()) {
				search.add(current.getLeft());
				search.add(current.getRight());
			} else {
				leafNodes.add(current);
			}
		}

		// Print out the map
		String[][] arr = new String[WIDTH + 1][HEIGHT + 1];

		// set all characters in array
		for (int i = 0; i < WIDTH + 1; i++) {
			for (int j = 0; j < HEIGHT + 1; j++) {
				arr[i][j] = "░";
			}
		}

		for (TreeNode<Container> treeNode : leafNodes) {
			treeNode.getData().fill_array_by_container(arr);
		}

		for (String[] strings : arr) {
			for (String string : strings) {
				System.out.print(string);
			}
			System.out.println();
		}
		root.printTree();

		SimpleImage image = new SimpleImage(WIDTH + 1, HEIGHT + 1);
		for (int i = 0; i <= WIDTH; i++) {
			for (int j = 0; j <= HEIGHT; j++) {
				if (arr[i][j].equals("░")) {
					image.set(i, j, Color.black);
				} else {
					image.set(i, j, Color.green);
				}
			}
		}

		image.save("current.png");
	}

}
