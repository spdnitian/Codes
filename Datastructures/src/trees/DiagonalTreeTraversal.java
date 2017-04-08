package trees;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class DiagonalTreeTraversal {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		Tree<Integer> tree = Tree.createEmptyTree();
		while(!"done".equalsIgnoreCase(input)) {
			String[] inputs = input.split(" ");
			String path = inputs[0];
			Integer data = Integer.parseInt(inputs[1]);
			tree.insert(path, data);
			input = br.readLine();
		}
		printDiagonalTraversal(tree);
	}
	
	public static void printDiagonalTraversal(Tree<Integer> tree) {
		HashMap<Integer, ArrayList<Integer>> diagonalMap = new HashMap<Integer, ArrayList<Integer>>();
		HashMap<Integer, ArrayList<Integer>> diagonalMapInOrder = new HashMap<Integer, ArrayList<Integer>>();
		levelOrderTraversalUtil(tree.getRoot(), diagonalMap);
		inorderTraversalUtil(tree.getRoot(), diagonalMapInOrder, 0);
		int totalDiagonals = diagonalMap.size();
		for(int diagonalNum = 0; diagonalNum < totalDiagonals; diagonalNum++) {
			ArrayList<Integer> elementsInDiagonal = diagonalMap.get(diagonalNum);
			System.out.println(Arrays.toString(elementsInDiagonal.toArray()));
		}
		System.out.println("--------------------------------------------------------------------------");
		totalDiagonals = diagonalMapInOrder.size();
		for(int diagonalNum = 0; diagonalNum < totalDiagonals; diagonalNum++) {
			ArrayList<Integer> elementsInDiagonal = diagonalMapInOrder.get(diagonalNum);
			System.out.println(Arrays.toString(elementsInDiagonal.toArray()));
		}
	}
	
	private static void levelOrderTraversalUtil(BinaryTreeNode<Integer> refNode, HashMap<Integer, ArrayList<Integer>> diagonalMap) {
		List<BinaryTreeNode<Integer>> nodes = new LinkedList<BinaryTreeNode<Integer>>();
		List<Integer> diagonals = new LinkedList<Integer>();
		diagonals.add(0);
		nodes.add(refNode);
		while (!nodes.isEmpty()) {
			BinaryTreeNode<Integer> node = nodes.remove(0);
			Integer diagonal = diagonals.remove(0);
			ArrayList<Integer> nodesInDiagonal = diagonalMap.get(diagonal);
			if(nodesInDiagonal == null) {
				nodesInDiagonal = new ArrayList<Integer>();
				diagonalMap.put(diagonal, nodesInDiagonal);
			}
			nodesInDiagonal.add(node.getData());
			if(node.getLeft() != null) {
				diagonals.add(diagonal + 1);
				nodes.add(node.getLeft());
			}
			if(node.getRight() != null) {
				diagonals.add(diagonal);
				nodes.add(node.getRight());
			}
		}
	}
	
	private static void inorderTraversalUtil(BinaryTreeNode<Integer> refNode, HashMap<Integer, ArrayList<Integer>> diagonalMap, Integer diagonalNum) {
		if(refNode != null) {
			ArrayList<Integer> nodesInDiagonal = diagonalMap.get(diagonalNum);
			if(nodesInDiagonal == null) {
				nodesInDiagonal = new ArrayList<Integer>();
				diagonalMap.put(diagonalNum, nodesInDiagonal);
			}
			nodesInDiagonal.add(refNode.getData());
			inorderTraversalUtil(refNode.getLeft(), diagonalMap, diagonalNum + 1);
			inorderTraversalUtil(refNode.getRight(), diagonalMap, diagonalNum);
		}
	}

}
