package Generate;

import java.util.Optional;

/**
 * @param <T> Type for this TreeNode's Data
 * @author Sam Harris
 * @version 1.0
 * <p>
 * TreeNode is used to build up a doubly linked tree of TreeNodes (arguably an undirected graph)
 * Unlike a normal binary tree, TreeNodes also contain a refernece to their parents.
 */
public class TreeNode<T> {

	// Data for this node
	private final T data;
	private final Optional<TreeNode<T>> parent;
	private Optional<TreeNode<T>> left;
	private Optional<TreeNode<T>> right;

	/**
	 * @param parent Parent of this TreeNode
	 * @param data   Data for this TreeNode
	 */
	public TreeNode(TreeNode<T> parent, T data) {
		this.parent = Optional.ofNullable(parent);
		this.data = data;
		this.left = Optional.empty();
		this.right = Optional.empty();
	}

	public int getLevel() {
		return parent.map(tTreeNode -> tTreeNode.getLevel() + 1).orElse(0);
	}

	public T getData() {
		return data;
	}

	public TreeNode<T> getLeft() {
		return left.orElse(null);
	}

	public void setLeft(TreeNode<T> treeNode) {
		this.left = Optional.ofNullable(treeNode);
	}

	public TreeNode<T> getRight() {
		return right.orElse(null);
	}

	public void setRight(TreeNode<T> treeNode) {
		this.right = Optional.ofNullable(treeNode);
	}

	public boolean hasChildren() {
		return left.isPresent() && right.isPresent();
	}

	public void printTree() {
		String spacer = Utils.repeat(this.getLevel(), "\t");
		System.out.println(spacer + data.toString());
		left.ifPresent(TreeNode::printTree);
		right.ifPresent(TreeNode::printTree);
	}
}
