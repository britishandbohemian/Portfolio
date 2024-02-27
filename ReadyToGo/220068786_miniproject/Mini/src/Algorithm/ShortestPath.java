package Algorithm;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import com.jwetherell.algorithms.data_structures.Graph;
import com.jwetherell.algorithms.data_structures.Graph.Edge;
import com.jwetherell.algorithms.data_structures.Graph.Vertex;

public class ShortestPath<T extends Comparable<T>> {
	
	Graph<T> graph;
	
	public ShortestPath(Graph<T> graph) {
		this.graph = graph;
	}
	
	public Map<Vertex<T>, Integer> getShortestPath(Vertex<T> source){
		
		//Intilialize all vertices distance to infinity
		Map<Vertex<T>, Integer> distances = new HashMap<>();
		
		for(Vertex<T> V : graph.getVertices()) {
			distances.put(V, Integer.MAX_VALUE);
		}
		//Set the source distance to 0;
		distances.put(source, 0);
		
		//A priority queue to store unvisited vertices
		PriorityQueue< Vertex<T>> unvisitedQueue = new PriorityQueue<>(Comparator.comparingInt(distances::get));
		unvisitedQueue.add(source);
		
		//Visit all the unvisited vertices
		while(!unvisitedQueue.isEmpty()) {
			Vertex<T> V = unvisitedQueue.poll();
			
			System.out.println(V.toString());
			
			//Stop if the next vertex is an infinity
			if(distances.get(V) == Integer.MAX_VALUE) {
				break;
			}
			
			//Visit all neighboring vertex
			for(Edge<T> e: graph.getEdges()) {
				Vertex<T> vertex = e.getToVertex();
				
				int alt = distances.get(V) + e.getCost();
				
				if (alt < distances.get(vertex)) {
                    distances.put(vertex, alt);
                    unvisitedQueue.remove(vertex); // Re-order vertex in queue
                    unvisitedQueue.add(vertex);
                }
				
				
			}
			
		}
		
		
		return distances;
		
	}

}
