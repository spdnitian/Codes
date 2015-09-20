package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

class Edge{
	int source;
	int destination;
	public Edge(int src, int dest) {
		source = src;
		destination = dest;
	}
}

class Graph{
	int vertices;
	List<Edge> edges;
	
	Graph(int v){
		vertices = v;
		edges = new ArrayList<Edge>();
	}
	
	public void addEdge(int src, int dest){
		Edge newEdge = new Edge(src, dest);
		edges.add(newEdge);
	}
}
public class TopologicalSorting {
	
	@SuppressWarnings("unchecked")
	public static ArrayList<Integer>[] createAdgacencyList(Graph graph){
		ArrayList<Integer>[] adjacencyList = new ArrayList[graph.vertices];
		for (Edge edge : graph.edges) {
			int src = edge.source;
			if(adjacencyList[src] == null){
				adjacencyList[src] = new ArrayList<Integer>();
			}
			adjacencyList[src].add(edge.destination);
		}
		return adjacencyList;
	}
	
	private static void topoSortUtil(ArrayList<Integer>[] adjacecnyList, Stack<Integer> topoStack, boolean[] isVisited, int vertex){
		if(isVisited[vertex]){
			return;
		}
		isVisited[vertex] = true;
		ArrayList<Integer> adjListOfVertex = adjacecnyList[vertex];
		if(adjListOfVertex != null){
			for (Integer destVertex : adjListOfVertex) {
				topoSortUtil(adjacecnyList, topoStack, isVisited, destVertex);
			}
		}
		topoStack.push(vertex);
	}
	
	public static int[] topologicalSort(Graph graph){
		boolean[] isVisited = new boolean[graph.vertices];
		ArrayList<Integer>[] adjacecnyList = createAdgacencyList(graph);
		Stack<Integer> topoStack = new Stack<Integer>();
		for (int startVertex = 0; startVertex < isVisited.length; startVertex++) {
			if(!isVisited[startVertex]){
				topoSortUtil(adjacecnyList, topoStack, isVisited, startVertex);;
			}
		}
		int[] topoSortedArr = new int[graph.vertices];
		int index = 0;
		while (!topoStack.isEmpty()) {
			topoSortedArr[index++] = topoStack.pop();
		}
		return topoSortedArr;
	}
	
	public static void main(String[] args) {
		Graph graph = new Graph(6);
		graph.addEdge(5, 2);
		graph.addEdge(5, 0);
		graph.addEdge(4, 1);
		graph.addEdge(4, 0);
		graph.addEdge(2, 3);
		graph.addEdge(3, 1);
		int[] topoSortList = topologicalSort(graph);
		System.out.println(Arrays.toString(topoSortList));
	}

}
