package graphalgos;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


class UndirectedGraph<T> {
	private Map<T, List<T>> adjencencyVertices;
	
	private int currentTime;
	
	private void findArticulationPoints(T currentVertex, T parentVertex, Map<T, Boolean> isVisted, Map<T, Integer> visitedTimes, Map<T, Integer> lowTimes,
			List<T> articulationPoints) {
		isVisted.put(currentVertex, true);
		visitedTimes.put(currentVertex, currentTime);
		lowTimes.put(currentVertex, currentTime);
		
		currentTime++;
		
		List<T> adjencenctVertices = adjencencyVertices.get(currentVertex);
		Integer unvisitedChildren = 0;
		boolean isCandidateArticulationPoint = false;
		if(!adjencenctVertices.isEmpty()) {
			for(T adjacentVertex : adjencenctVertices) {
				if(!isVisted(isVisted, adjacentVertex)) {
					unvisitedChildren++;
					findArticulationPoints(adjacentVertex, currentVertex, isVisted, visitedTimes, lowTimes, articulationPoints);
					if(visitedTimes.get(currentVertex) <= lowTimes.get(adjacentVertex)) {
						isCandidateArticulationPoint = true;
					}else {
						lowTimes.put(currentVertex, Math.min(lowTimes.get(currentVertex), lowTimes.get(adjacentVertex)));
					}
				}else {
					lowTimes.put(currentVertex, Math.min(lowTimes.get(currentVertex), visitedTimes.get(adjacentVertex)));
				}
			}
		}
		if((parentVertex == null && unvisitedChildren >= 2) 
				|| (parentVertex != null && isCandidateArticulationPoint)) {
			articulationPoints.add(currentVertex);
		}
		
	}

	private boolean isVisted(Map<T, Boolean> isVisted, T vertex) {
		if(isVisted.get(vertex) != null) {
			return isVisted.get(vertex);
		}
		return false;
	}

	private void addEdgeUtil(T endpoint1, T endpoint2) {
		List<T> vertices = adjencencyVertices.get(endpoint1);
		if(vertices == null) {
			vertices = new LinkedList<>();
			adjencencyVertices.put(endpoint1, vertices);
			
		}
		vertices.add(endpoint2);
	}
	
	public UndirectedGraph() {
		adjencencyVertices = new HashMap<>();
	}
	
	public void addEdge(T endpoint1, T endpoint2) {
		addEdgeUtil(endpoint1, endpoint2);
		addEdgeUtil(endpoint2, endpoint1);
	}
	
	public List<T> findArticulationPoints(){
		List<T> articulationPoints = new LinkedList<T>();
		Map<T, Integer> lowTimes = new HashMap<>();
		Map<T, Boolean> isVisted = new HashMap<>();
		Map<T, Integer> visitedTimes = new HashMap<>();
		T startVertex = adjencencyVertices.keySet().iterator().next();
		findArticulationPoints(startVertex, null, isVisted, visitedTimes, lowTimes, articulationPoints);
		return articulationPoints;
	}

}

public class ArticulationPoints {
	public static void main(String[] args) {
		UndirectedGraph<Integer> g1 = new UndirectedGraph<>();
        g1.addEdge(1, 0);
        g1.addEdge(0, 2);
        g1.addEdge(2, 1);
        g1.addEdge(0, 3);
        g1.addEdge(3, 4);
        System.out.println(g1.findArticulationPoints());
 
        UndirectedGraph<Integer> g2 = new UndirectedGraph<>();
        g2.addEdge(0, 1);
        g2.addEdge(1, 2);
        g2.addEdge(2, 3);
        System.out.println(g2.findArticulationPoints());
 
        UndirectedGraph<Integer> g3 = new UndirectedGraph<>();
        g3.addEdge(0, 1);
        g3.addEdge(1, 2);
        g3.addEdge(2, 0);
        g3.addEdge(1, 3);
        g3.addEdge(1, 4);
        g3.addEdge(1, 6);
        g3.addEdge(3, 5);
        g3.addEdge(4, 5);
        System.out.println(g3.findArticulationPoints());
	}
}
