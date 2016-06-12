package LinerDS;

public class LinkedList {
	private LinkNode headNode;
	
	class LinkNode{
		private int nodeVal;
		LinkNode nextNode;
		public LinkNode(int nodeVal) {
			this.nodeVal = nodeVal;
		}
		
		public int getValue() {
			return nodeVal;
		}
		
		public void setValue(int val) {
			nodeVal = val;
		}
	}
	
	public void insertNode(int nodeVal) {
		LinkNode node = new LinkNode(nodeVal);
		LinkNode iterator;
		if(headNode == null){
			headNode = node;
		}else {
			iterator = headNode;
			while(iterator.nextNode != null){
				iterator = iterator.nextNode;
			}
			iterator.nextNode = node;
		}
	}
	
	public void insertNode(int nodeVal,int index) {
		LinkNode node = new LinkNode(nodeVal);
		LinkNode iterator;
		if(headNode == null){
			this.insertNode(nodeVal);
		}else {
			iterator = headNode;
			int start = 0;
			while(start <= index){
				iterator = iterator.nextNode;
				start++;
			}
			iterator.nextNode = node;
		}
	}
	
	public void deleteNode() {
		if(headNode == null){
			return;
		}else {
			LinkNode iterator = headNode;
			LinkNode node = iterator;
			while(iterator.nextNode != null){
				node = iterator;
				iterator = iterator.nextNode;
			}
			if(node == headNode){
				headNode = null;
			}else {
				node.nextNode = null;
			}
		}
	}
	
	public LinkNode getHead(){
		return headNode;
	}
	
	public void setHead(LinkNode newHead){
		LinkNode oldHead = headNode;
		if(newHead == null){
			headNode = null;
		}else{
			newHead.nextNode = oldHead;
			headNode = newHead;
		}
	}
	public void printList(){
		printList(headNode);
	}
	private void printList(LinkNode startNode) {
		if(startNode != null){
			System.out.print(startNode.getValue() + " ");
			printList(startNode.nextNode);
		}
	}

	public int size() {
		return size(headNode);
	}
	private int size(LinkNode startNode) {
		if(startNode == null){
			return 0;
		}
		return 1 + size(startNode.nextNode);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
