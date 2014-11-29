package maxcapacitypaths;

import graph.DirectedGraphAPI;
import graph.Edge;
import graph.GraphGenerator;
import graph.UndirectedGraphAPI;
import heap.MaxHeapAPI;
import heap.MinHeapAPI;

import java.util.ArrayList;

public class MaxCapacityPathAlgo2 {

	private static int UNSEEN = 0;
	private static int FRINGE = 1;
	private static int INTREE = 2;
	
	private static int status[];
	private static int capacity[];
	private static int dad[];
	private static MaxHeapAPI edgeList;
	
	public static void main(String[] args) throws Exception {
		UndirectedGraphAPI graph = new UndirectedGraphAPI(7);
		graph.addEdge(0, 1, 40);
		graph.addEdge(0, 2, 8);
		graph.addEdge(0, 3, 11);
		graph.addEdge(1, 2, 29);
		graph.addEdge(1, 5, 17);
		graph.addEdge(2, 5, 31);
		graph.addEdge(2, 3, 3);
		graph.addEdge(3, 4, 46);
		graph.addEdge(4, 5, 40);
		graph.addEdge(4, 6, 15);
		graph.addEdge(5, 6, 53);
		long startTime = System.currentTimeMillis();
		UndirectedGraphAPI graph1 = GraphGenerator.generateUndirectedGraph(5000);
		long graphGenerationTime = System.currentTimeMillis();
		System.out.println();
		System.out.println("Graph Genertion time = " + (graphGenerationTime - startTime));
		executeDijkstra(graph1, 0, 5);
		long dijkstraTime = System.currentTimeMillis();
		System.out.println("Dijkstra Algorithm time = " + (dijkstraTime - graphGenerationTime));
		System.out.println();
	}

	public static int executeDijkstra(UndirectedGraphAPI graph, int source, int destination) throws Exception {		
		status = new int[graph.V()];
		capacity = new int[graph.V()];
		dad = new int[graph.V()];
		edgeList = new MaxHeapAPI(graph.E());
		
		//Set all vertices to unseen
		for(int i=0; i<graph.V(); i++){
			status[i] = UNSEEN;
			capacity[i] = Integer.MAX_VALUE;
		}
		
		//add source vertex
		status[source] = INTREE;
		capacity[source] = Integer.MAX_VALUE;
		dad[source] = -1;
		
		//change neighbors of source to fringes
		ArrayList<Edge> sourceList = graph.getAdj()[source];
		for(Edge edge : sourceList){
			int w = edge.getOtherVertex(source);
			status[w] = FRINGE;
			capacity[w] = edge.getWeight();
			dad[w] = source;
			edgeList.insert(edge);
		}
		
		while(!edgeList.isEmpty()){			
			Edge reqEdge = null;
			reqEdge = edgeList.maximum();
			int maxWeight = reqEdge.getWeight();
			
			int v = reqEdge.getOneVertex();
			int w = reqEdge.getOtherVertex(v);
			int fringeVertex;
			if(status[v] == INTREE){
				fringeVertex = w;
			}else{
				fringeVertex = v;
			}
			edgeList.delete(1);
			
			//add neighbors as fringes
			ArrayList<Edge> list = graph.getAdj()[fringeVertex];
			for(Edge edge : list){
				int w1 = edge.getOtherVertex(fringeVertex);
				if(status[w1] == UNSEEN){
					status[w1] = FRINGE;									
					capacity[w1] = Math.min(capacity[fringeVertex], edge.getWeight());
					edgeList.insert(edge);
					dad[w1] = fringeVertex;
				}else if(status[w1] == FRINGE && capacity[w1] < Math.min(capacity[fringeVertex], edge.getWeight())){
					for(int i=1; i<= edgeList.getHeapCount(); i++){
						Edge edge2 = edgeList.getHeap()[i];
						int v2 = edge2.getOneVertex();
						int w2 = edge2.getOtherVertex(v2);
						if((v2 == w1 && capacity[w1] == edge2.getWeight()) || (w2 == w1 && capacity[w1] == edge2.getWeight())){
							edgeList.delete(i);
							break;
						}
					}							
					capacity[w1] = Math.min(capacity[fringeVertex], edge.getWeight());
					edgeList.insert(edge);
					dad[w1] = fringeVertex;
				}
				status[fringeVertex] = INTREE;
			}
		}
		return capacity[destination];
	}
	
	public static int executeDijkstra(DirectedGraphAPI graph, int source, int destination) throws Exception {		
		status = new int[graph.V()];
		capacity = new int[graph.V()];
		dad = new int[graph.V()];
		edgeList = new MaxHeapAPI(graph.E());
		
		//Set all vertices to unseen
		for(int i=0; i<graph.V(); i++){
			status[i] = UNSEEN;
			capacity[i] = Integer.MAX_VALUE;
		}
		
		//add source vertex
		status[source] = INTREE;
		capacity[source] = Integer.MAX_VALUE;
		dad[source] = -1;
		
		//change neighbors of source to fringes
		ArrayList<Edge> sourceList = graph.getAdj()[source];
		for(Edge edge : sourceList){
			int w = edge.getOtherVertex(source);
			status[w] = FRINGE;
			capacity[w] = edge.getWeight();
			dad[w] = source;
			edgeList.insert(edge);
		}
		
		while(!edgeList.isEmpty()){	
//			System.out.println("Heap Count: " + edgeList.getHeapCount());
			Edge reqEdge = null;
			reqEdge = edgeList.maximum();
			int maxWeight = reqEdge.getWeight();
			
			int v = reqEdge.getOneVertex();
			int w = reqEdge.getOtherVertex(v);
			int fringeVertex;
			if(status[v] == INTREE){
				fringeVertex = w;
			}else{
				fringeVertex = v;
			}
			edgeList.delete(1);
			
			//add neighbors as fringes
			ArrayList<Edge> list = graph.getAdj()[fringeVertex];
			for(Edge edge : list){
				int w1 = edge.getOtherVertex(fringeVertex);
				if(status[w1] == UNSEEN){
					status[w1] = FRINGE;					
					capacity[w1] = Math.min(capacity[fringeVertex], edge.getWeight());
					edgeList.insert(edge);
					dad[w1] = fringeVertex;
				}else if(status[w1] == FRINGE && capacity[w1] < Math.min(capacity[fringeVertex], edge.getWeight())){
					for(int i=1; i<= edgeList.getHeapCount(); i++){
						Edge edge2 = edgeList.getHeap()[i];
						int v2 = edge2.getOneVertex();
						int w2 = edge2.getOtherVertex(v2);
						if((v2 == w1 && capacity[w1] == edge2.getWeight())){
							edgeList.delete(i);
							break;
						}
					}								
					capacity[w1] = Math.min(capacity[fringeVertex], edge.getWeight());
					edgeList.insert(edge);
					dad[w1] = fringeVertex;
				}
				status[fringeVertex] = INTREE;
			}
		}
		return capacity[destination];
	}
}
