package trees;

class BinaryTreeNode<T>{
	private T data;
	private BinaryTreeNode<T> left;
	private BinaryTreeNode<T> right;
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public BinaryTreeNode<T> getLeft() {
		return left;
	}
	public void setLeft(BinaryTreeNode<T> left) {
		this.left = left;
	}
	public BinaryTreeNode<T> getRight() {
		return right;
	}
	public void setRight(BinaryTreeNode<T> right) {
		this.right = right;
	}
	public static <T> BinaryTreeNode<T> createNode(T data) {
		BinaryTreeNode<T> node = new BinaryTreeNode<T>();
		node.setData(data);
		return node;
	}
}

public class Tree<T> {
	private BinaryTreeNode<T> root;

	public BinaryTreeNode<T> getRoot() {
		return root;
	}

	public void setRoot(BinaryTreeNode<T> root) {
		this.root = root;
	}
	
	public void insert(String path,T data) {
		BinaryTreeNode<T> newNode = BinaryTreeNode.createNode(data);
		if("root".equalsIgnoreCase(path)) {
			root = newNode;
		}else {
			int index = 0, strlen = path.length();
			BinaryTreeNode<T> node = root;
			if(node == null) {
				System.err.println("Please create root data.");
				return;
			}
			while(index < strlen - 1) {
				if("l".equalsIgnoreCase(path.substring(index, index + 1))) {
					node = node.getLeft();
				}else if("r".equalsIgnoreCase(path.substring(index, index + 1))) {
					node = node.getRight();
				}
				index++;
			}
			if("l".equalsIgnoreCase(path.substring(strlen - 1, strlen))) {
				node.setLeft(newNode);
			}else if("r".equalsIgnoreCase(path.substring(strlen - 1, strlen))) {
				node.setRight(newNode);
			}
		}
	}
	
	public static <T> Tree<T> createEmptyTree() {
		return new Tree<T>();
	}
}
