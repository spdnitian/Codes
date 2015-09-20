package trees;

class Node {
    int data;
    Node small;
    Node large;
    
    public Node(int data) {
        this.data = data;
        small = null;
        large = null;
    }
}

public class TressToDList {
	Node head;
	Node prevNode;
	public void createDL(Node node){
		if(node != null){
			createDL(node.small);
			if(head == null){
				head = node;
			}
			node.small = prevNode;
			if(prevNode != null){
				prevNode.large = node;
			}
			prevNode = node;
			createDL(node.large);
		}
	}
	
	 public static void treeInsert(Node root, int newData) {
	        if (newData<=root.data) {
	            if (root.small!=null) treeInsert(root.small, newData);
	            else root.small = new Node(newData);
	        }
	        else {
	            if (root.large!=null) treeInsert(root.large, newData);
	            else root.large = new Node(newData);
	        }
	    }
	    
	    
	    // Do an inorder traversal to print a tree
	    // Does not print the ending "\n"
	    public static void printTree(Node root) {
	        if (root==null) return;
	        printTree(root.small);
	        System.out.print(Integer.toString(root.data) + " ");
	        printTree(root.large);
	    }
	    
	    
	    // Do a traversal of the list and print it out
	    public static void printList(Node head) {
	        Node current = head;
	        
	        while (current != null) {
	            System.out.print(Integer.toString(current.data) + " ");
	            current = current.large;
	            if (current == head) break;
	        }
	        
	        System.out.println();
	    }
	            
	    
	    // Demonstrate tree->list with the list 1..5
	    public static void main(String[] args) {
	    
	        // first build the tree shown in the problem document
	        // http://cslibrary.stanford.edu/109/
	        Node root = new Node(4);
	        treeInsert(root, 2);
	        treeInsert(root, 1);
	        treeInsert(root, 3);
	        treeInsert(root, 5);
	        
	        System.out.println("tree:");
	        printTree(root);   // 1 2 3 4 5
	        System.out.println();
	        
	        System.out.println("list:");
	        TressToDList tressToDList = new TressToDList();
	        tressToDList.createDL(root);
	        printList(tressToDList.head);   // 1 2 3 4 5   yay!
	    }
}
