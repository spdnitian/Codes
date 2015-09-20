package trees;

import utils.CommonUtilities;

class TreeNode{
	int nodeVal;
	TreeNode left;
	TreeNode right;
	TreeNode parent;
	int height;
	
	public TreeNode(int val) {
		nodeVal = val;
		left = right = null;
		height = 0;
	}
}
public class AVLTree {
	
	TreeNode root;
	
	private TreeNode constructNode(int val){
		TreeNode treeNode = new TreeNode(val);
		return treeNode;
	}
	
	public void insertNode(int val){
		TreeNode node = constructNode(val);
		insertIntoAVL(root, node);
	}
	
	
	public void printTree(TreeNode node) {
		if(node != null){
			printTree(node.left);
			int parentVal = node.parent != null ? node.parent.nodeVal : -1;
			System.out.println(node.nodeVal + "		" + node.height  + "	" + parentVal + "	" + getSubTreeHeightsDiff(node));
			printTree(node.right);
		}
	}
	
	private int insertIntoAVL(TreeNode node, TreeNode nodeToInsert) {
		int height = 0;
		if(root == null){
			root = nodeToInsert;
			return height;
		}else {
			height = 1;
			if(nodeToInsert.nodeVal < node.nodeVal){
				if(node.left == null){
					nodeToInsert.parent = node;
					node.left = nodeToInsert;
				}else {
					height = insertIntoAVL(node.left, nodeToInsert) + 1;
				}
			}else if (nodeToInsert.nodeVal > node.nodeVal) {
				if(node.right == null){
					nodeToInsert.parent = node;
					node.right = nodeToInsert;
				}else {
					height = insertIntoAVL(node.right, nodeToInsert) + 1;
				}
			}
			if(height > node.height){
				node.height = height;
			}
		}
		if(!isBalanced(node)){
			return balanceNode(node);
		}
		return node.height;
	}

	private int balanceNode(TreeNode node) {
		int heightDiff = getSubTreeHeightsDiff(node);
		int childNodeHeightDiff = 0;
		TreeNode rotatedBalancedRoot = null;
		if(heightDiff > 0){ // Node is left- heavy
			TreeNode leftChildNode = node.left;
			childNodeHeightDiff = getSubTreeHeightsDiff(leftChildNode);
			if(childNodeHeightDiff > 0){ // Node is Left-to-Left heavy
				rotatedBalancedRoot = rotateToRight(node);
			}else if (childNodeHeightDiff < 0) { // Node is Left-to-Right heavy
				rotateToLeft(leftChildNode);
				rotatedBalancedRoot = rotateToRight(node);
			}
		}else if(heightDiff < 0) { // Node is right - heavy
			TreeNode rightChildNode = node.right;
			childNodeHeightDiff = getSubTreeHeightsDiff(rightChildNode);
			if(childNodeHeightDiff < 0){ // Node is Right-to-Reft heavy
				rotatedBalancedRoot = rotateToLeft(node);
			}else if (childNodeHeightDiff > 0) { // Node is Right-to-Left heavy
				rotateToRight(rightChildNode);
				rotatedBalancedRoot = rotateToLeft(node);
			}
		}
		int newRootHeight = rotatedBalancedRoot != null ? rotatedBalancedRoot.height : 0;
		return newRootHeight;
	}
	
	private int getMaxOfChildSubTreeHeights(TreeNode node){
		int leftChildSubTreeHeight = node.left == null ? 0 : node.left.height + 1;
		int rightChildSubTreeHeight = node.right == null ? 0 : node.right.height + 1;
		return CommonUtilities.getMax(leftChildSubTreeHeight, rightChildSubTreeHeight);
	}

	private TreeNode rotateToLeft(TreeNode node) {
		TreeNode nodeParent = node.parent;
		boolean isRightChild = false;
		if(nodeParent != null){
			isRightChild = (nodeParent.right == node);
		}
		TreeNode nodeRightChild = node.right;
		TreeNode nodeRightLeftChild = nodeRightChild.left;
		nodeRightChild.left = node;
		node.right = nodeRightLeftChild;
		if(nodeRightLeftChild != null){
			nodeRightLeftChild.parent = node;
		}
		node.parent = nodeRightChild;
		nodeRightChild.parent = nodeParent;
		node.height = getMaxOfChildSubTreeHeights(node);
		nodeRightChild.height = getMaxOfChildSubTreeHeights(nodeRightChild);
		if(nodeParent == null){
			this.root = nodeRightChild;
		}else{
			if(isRightChild){
				nodeParent.right = nodeRightChild;
			}else{
				nodeParent.left = nodeRightChild;
			}
		}
		return nodeRightChild;
	}

	private TreeNode rotateToRight(TreeNode node) {
		TreeNode nodeParent = node.parent;
		boolean isLeftChild = false;
		if(nodeParent != null){
			isLeftChild = (nodeParent.left == node);
		}
		TreeNode nodeLeftChild = node.left;
		TreeNode nodeLeftRightChild = nodeLeftChild.right;
		nodeLeftChild.right = node;
		node.left = nodeLeftRightChild;
		if(nodeLeftRightChild != null){
			nodeLeftRightChild.parent = node;
		}
		node.parent = nodeLeftChild;
		nodeLeftChild.parent = nodeParent;
		node.height = getMaxOfChildSubTreeHeights(node);
		nodeLeftChild.height = getMaxOfChildSubTreeHeights(nodeLeftChild);
		if(nodeParent == null){
			this.root = nodeLeftChild;
		}else{
			if(isLeftChild){
				nodeParent.left = nodeLeftChild;
			}else{
				nodeParent.right = nodeLeftChild;
			}
		}
		return nodeLeftChild;
	}

	private boolean isBalanced(TreeNode node) {
		int diffMod = Math.abs(getSubTreeHeightsDiff(node));
		return diffMod <= 1;
	}

	private int getSubTreeHeightsDiff(TreeNode node) {
		int leftHeight = node.left != null ? node.left.height + 1: 0;
		int rightHeight = node.right != null ? node.right.height + 1: 0;
		return leftHeight - rightHeight;
	}

	public static void main(String[] args){
		AVLTree avlTree = new AVLTree();
		int[] elements  = CommonUtilities.getUniqueRandomArr(10);
		for (int element : elements) {
			System.out.print(element + " ");
			avlTree.insertNode(element);
		}
		System.out.println();
		System.out.println("Node Value" + " " + "Node Height"  + " " + "Parent Value"+" "+"Height Diffs");
		avlTree.printTree(avlTree.root);
	}
}
