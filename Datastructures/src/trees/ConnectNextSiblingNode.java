package trees;

import trees.data.TreeNode;

public class ConnectNextSiblingNode {
	
	public static <T> void connectNextSiblingNode(TreeNode<T> currentLevelStartNode) {
		if(currentLevelStartNode != null) {
			TreeNode<T> nextLevelStartNode = null;
			TreeNode<T> lastNode = null;
			TreeNode<T> node = currentLevelStartNode;
			while(node != null) {
				if(node.getLeftChild() != null) {
					if(nextLevelStartNode == null) {
						nextLevelStartNode = node.getLeftChild();
					}
					if(lastNode != null) {
						lastNode.setNextSibling(node.getLeftChild());
					}
					lastNode = node.getLeftChild();
				}
				if(node.getRightChild() != null) {
					if(nextLevelStartNode == null) {
						nextLevelStartNode = node.getRightChild();
					}
					if(lastNode != null) {
						lastNode.setNextSibling(node.getRightChild());
					}
					lastNode = node.getRightChild();
				}
				node = node.getNextSibling();
			}
			connectNextSiblingNode(nextLevelStartNode);
		}
	}
	
	public static <T> void printSiblingNodes(TreeNode<T> currentLevelStartNode) {
		if(currentLevelStartNode != null) {
			TreeNode<T> nextLevelStartNode = null;
			TreeNode<T> node = currentLevelStartNode;
			while(node != null) {
				if(node.getLeftChild() != null) {
					if(nextLevelStartNode == null) {
						nextLevelStartNode = node.getLeftChild();
					}
				}
				if(node.getRightChild() != null) {
					if(nextLevelStartNode == null) {
						nextLevelStartNode = node.getRightChild();
					}
				}
				System.out.print(node.getData() + " ");
				node = node.getNextSibling();
			}
			System.out.println();
			printSiblingNodes(nextLevelStartNode);
		}
	}

	public static void main(String[] args) {
		TreeNode<Integer> rootNode = TreeNode.createNode(1);
		rootNode.setLeftChild(TreeNode.createNode(2));
		rootNode.setRightChild(TreeNode.createNode(3));
		rootNode.getLeftChild().setLeftChild(TreeNode.createNode(4));
		rootNode.getLeftChild().setRightChild(TreeNode.createNode(5));
		rootNode.getRightChild().setLeftChild(TreeNode.createNode(6));
		rootNode.getRightChild().setRightChild(TreeNode.createNode(7));
		rootNode.getLeftChild().getLeftChild().setLeftChild(TreeNode.createNode(8));
		rootNode.getLeftChild().getLeftChild().setRightChild(TreeNode.createNode(9));
		rootNode.getRightChild().getRightChild().setLeftChild(TreeNode.createNode(10));
		rootNode.getRightChild().getRightChild().setRightChild(TreeNode.createNode(11));
		connectNextSiblingNode(rootNode);
		printSiblingNodes(rootNode);
	}

}
