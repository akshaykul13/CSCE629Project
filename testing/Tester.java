package testing;

import java.util.Random;

import maxcapacitypaths.MaxCapacityPathAlgo1;
import maxcapacitypaths.MaxCapacityPathAlgo2;
import maxcapacitypaths.MaxCapacityPathAlgo3;
import graph.DirectedGraphAPI;
import graph.GraphGenerator;
import graph.UndirectedGraphAPI;

public class Tester {

	private static int NUMBER_OF_TEST_GRAPHS = 1;
	private static int NUMBER_OF_VERTICES = 5000;	
	
	public static void main(String[] args) throws Exception {		
		for(int i=0; i<NUMBER_OF_TEST_GRAPHS; i++){			
			System.out.println("TestCase: " + (i+1));
			long startTime = System.nanoTime();
			UndirectedGraphAPI undirectedGraph = GraphGenerator.generateUndirectedGraph(NUMBER_OF_VERTICES);			
			Random randomGenerator = new Random();
			int source = randomGenerator.nextInt(NUMBER_OF_VERTICES);
			int destination = -1;
			while(true){
				destination = randomGenerator.nextInt(NUMBER_OF_VERTICES);;
				if(source != destination)
					break;
			}
			long undirectedGraphGenTime = System.nanoTime();
			System.out.println("Sparse undirected graph generation time: " + (undirectedGraphGenTime-startTime));
			int sparseCapacity1 = MaxCapacityPathAlgo1.executeDijkstra(undirectedGraph, source, destination);
			long undirectedAlgo1Time = System.nanoTime();
			System.out.println("Undirected Algo1 Time: " + (undirectedAlgo1Time-undirectedGraphGenTime));
			System.out.println("Sparse Capacity 1: " + sparseCapacity1);
			int sparseCapacity2 = MaxCapacityPathAlgo2.executeDijkstra(undirectedGraph, source, destination);
			long undirectedAlgo2Time = System.nanoTime();
			System.out.println("Undirected Algo2 Time: " + (undirectedAlgo2Time-undirectedAlgo1Time));
			System.out.println("Sparse Capacity 2: " + sparseCapacity2);
			MaxCapacityPathAlgo3.executeAlgorithm(undirectedGraph, source, destination);
			long undirectedAlgo3Time = System.nanoTime();
			System.out.println("Undirected Algo3 Time: " + (undirectedAlgo3Time-undirectedAlgo2Time));
			System.out.println();
			
			DirectedGraphAPI directedGraph = GraphGenerator.generateDirectedGraph(NUMBER_OF_VERTICES);
			long directedGraphGenTime = System.nanoTime();
			System.out.println("Dense directed graph generation time: " + (directedGraphGenTime-undirectedAlgo3Time));
			int denseCapacity1 = MaxCapacityPathAlgo1.executeDijkstra(directedGraph, source, destination);
			long directedAlgo1Time = System.nanoTime();
			System.out.println("Directed Algo1 Time: " + (directedAlgo1Time-directedGraphGenTime));
			System.out.println("Dense Capacity 1: " + denseCapacity1);
			int denseCapacity2 = MaxCapacityPathAlgo2.executeDijkstra(directedGraph, source, destination);
			long directedAlgo2Time = System.nanoTime();
			System.out.println("Directed Algo2 Time: " + (directedAlgo2Time-directedAlgo1Time));
			System.out.println("Dense Capacity 2: " + denseCapacity2);
			MaxCapacityPathAlgo3.executeAlgorithm(directedGraph, source, destination);
			long directedAlgo3Time = System.nanoTime();
			System.out.println("Directed Algo3 Time: " + (directedAlgo3Time-directedAlgo2Time));
			System.out.println();
			System.out.println("--------------------------------------------------------------------------------------------");
		}			
	}
}
