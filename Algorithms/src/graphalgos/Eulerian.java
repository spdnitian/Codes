package graphalgos;

import searchNsort.CommonUtilities;

class Graph{
	int[][] adjMatrix;
	public Graph(int V) {
		adjMatrix = new int[V][V];
		for(int src = 0; src < V; src++){
			for (int dest = 0; dest < V; dest++) {
				if(src != dest){
					adjMatrix[src][dest] = -1;
				}
			}
		}
	}
	public void addEdge(int src, int dest){
		adjMatrix[src][dest] = 1;
	}
}
public class Eulerian {
	private static boolean isConnected(int[][] graph, int[] degreeExplored) {
		int startVertex = -1;
		for(int vertex = 0; vertex < graph.length; vertex++){
			if(!CommonUtilities.isOdd(degreeExplored[vertex])){
				startVertex = vertex;
				break;
			}
		}
		if(startVertex == -1){
			return false;
		}
		boolean[] isVisited = new boolean[graph.length];
		startDFS(graph, startVertex, new int[graph.length], isVisited);
		for(int vertex = 0; vertex < graph.length; vertex++){
			if(!isVisited[vertex]){
				return false;
			}
		}
		return true;
	}
	private static int[] DFSUtil(int[][] graph) {
		int[] degreesExplored = new int[graph.length];
		boolean[] isVisited = new boolean[graph.length];
		for(int vertex = 0; vertex < graph.length; vertex++){
			startDFS(graph, vertex, degreesExplored, isVisited);
		}
		return degreesExplored;
	}
	private static void startDFS(int[][] graph, int start, int[] degreesExplored, boolean[] isVisited) {
		if(!isVisited[start]){
			isVisited[start] = true;
			for(int vertex = 0; vertex < graph.length; vertex++){
				if(vertex != start && graph[start][vertex] != -1){
					degreesExplored[start]++;
					degreesExplored[vertex]++;
					startDFS(graph, vertex, degreesExplored, isVisited);
				}
			}
		}
	}
	/*
	 * A graph is eulerian if it contains a eulerian cycle.
	 * A eulerian cycle is a CYCLE which visits ALL EDGES EXACTLY ONCE.
	 * */
	public static boolean isEulerian(int[][] graph){
		boolean isEulerian = true;
		int[] degreesExplored = DFSUtil(graph);
		/*
		 * 1. All edges with non-zero degrees should be connected.
		 * */
		if(!isConnected(graph, degreesExplored)){
			return false;
		}
		
		/*
		 * 2. All of their degrees should be even.
		 * */
		for(int vertex = 0; vertex < graph.length; vertex++){
			isEulerian = isEulerian && !CommonUtilities.isOdd(degreesExplored[vertex]);
		}
		//System.out.println(Arrays.toString(degreesExplored));
		return isEulerian;
	}
	/*
	 * A graph is semi-eulerian if it contains a eulerian path.
	 * A eulerian path is a PATH which visits ALL EDGES EXACTLY ONCE.
	 * */
	public static boolean isSemiEulerian(int[][] graph){
		boolean isSemiEulerian = true;
		int[] degreesExplored = DFSUtil(graph);
		/*
		 * 1. All edges with non-zero degrees should be connected.
		 * */
		if(!isConnected(graph, degreesExplored)){
			return false;
		}
		
		/*
		 * 2. if 0/2 have ODD degree and rest have even degree.only one vertex with odd degree not possible in undirected graph.
		 * */
		int numOfOdd = 0;
		for(int vertex = 0; vertex < graph.length; vertex++){
			if(CommonUtilities.isOdd(degreesExplored[vertex])){
				numOfOdd++;
				if(numOfOdd > 2){
					return false; 
				}
			}
		}
		return isSemiEulerian;
	}
	
	public static void main(String[] args) {
		int V = 5;
		Graph g1 = new Graph(V);
	    g1.addEdge(1, 0);
	    g1.addEdge(0, 2);
	    g1.addEdge(2, 1);
	    g1.addEdge(0, 3);
	    g1.addEdge(3, 4);
	    /*g1.addEdge(2, 4);
	    g1.addEdge(3, 1);*/
	    System.out.println(isSemiEulerian(g1.adjMatrix));
	    
	    Graph g2= new Graph(V);
	    g2.addEdge(1, 0);
	    g2.addEdge(0, 2);
	    g2.addEdge(2, 1);
	    g2.addEdge(0, 3);
	    g2.addEdge(3, 4);
	    g2.addEdge(4, 0);
	    
	    System.out.println(isEulerian(g2.adjMatrix));
	}
}
