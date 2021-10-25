package Generate;

/**
 * @author Sam Harris
 * @version 1.0
 * <p>
 * This class represents a grid (or subgrid) containing it's start and endpoints in the x and z axes.
 */
public class Container {

	static final float RATIO = 0.45f;
	static final String WALL = "▓";

	int left;
	int right;
	int top;
	int bottom;

	public Container(int left, int right, int top, int bottom) {
		this.left = left;
		this.right = right;
		this.top = top;
		this.bottom = bottom;
	}

	// Splits the container into two containers, one on the left and one on the right.
	public static void splitContainer(TreeNode<Container> node) {
		Container container = node.getData();
		int mid;
		float r1, r2;

		// Split the Containers
		switch (Direction.getRandomDirection()) {
			case HORIZONTAL:

				mid = Utils.randomInRange(container.left, container.right);
				r1 = Math.abs((float) (container.left - mid) / (float) (container.top - container.bottom));
				r2 = Math.abs((float) (container.right - mid) / (float) (container.top - container.bottom));

				node.setLeft(new TreeNode<>(node, new Container(container.left, mid, container.top, container.bottom)));
				node.setRight(new TreeNode<>(node, new Container(mid, container.right, container.top, container.bottom)));
				break;

			case VERTICAL:

				mid = Utils.randomInRange(container.top, container.bottom);
				r1 = Math.abs((float) (container.top - mid) / (container.left - container.right));
				r2 = Math.abs((float) (container.bottom - mid) / (container.left - container.right));

				node.setLeft(new TreeNode<>(node, new Container(container.left, container.right, container.top, mid)));
				node.setRight(new TreeNode<>(node, new Container(container.left, container.right, mid, container.bottom)));
				break;

			default:
				throw new IllegalStateException("Unexpected value: " + Direction.getRandomDirection());
		}

		if (r1 < RATIO || r2 < RATIO) {
			Container.splitContainer(node);
		}
	}

	public void fill_array_by_container(String[][] arr) {

		// Fill in the Top and Bottom Walls of the Container
		for (int i = left; i <= right; i++) {
			arr[top][i] = WALL;
			arr[bottom][i] = WALL;
		}

		// Fill in the Left and Right
		for (int i = bottom; i <= top; i++) {
			arr[i][left] = WALL;
			arr[i][right] = WALL;
		}
	}

	public String toString() {
		return "(" + left + ", " + right + ", " + top + ", " + bottom + ")";
	}

}