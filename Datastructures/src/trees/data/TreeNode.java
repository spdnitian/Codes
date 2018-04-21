package trees.data;

public class TreeNode<T>{
	private T data;
	private TreeNode<T> leftChild;
	private TreeNode<T> rightChild;
	private TreeNode<T> nextSibling;
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public TreeNode<T> getLeftChild() {
		return leftChild;
	}
	public void setLeftChild(TreeNode<T> leftChild) {
		this.leftChild = leftChild;
	}
	public TreeNode<T> getRightChild() {
		return rightChild;
	}
	public void setRightChild(TreeNode<T> rightChild) {
		this.rightChild = rightChild;
	}
	public TreeNode<T> getNextSibling() {
		return nextSibling;
	}
	public void setNextSibling(TreeNode<T> nextSibling) {
		this.nextSibling = nextSibling;
	}
	
	public static <T> TreeNode<T> createNode(T data){
		TreeNode<T> treeNode = new TreeNode<>();
		treeNode.setData(data);
		return treeNode;
	}
}
