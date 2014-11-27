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
			UndirectedGraphAPI graph = GraphGenerator.generateUndirectedGraph(NUMBER_OF_VERTICES);
			Random randomGenerator = new Random();
			int source = randomGenerator.nextInt(NUMBER_OF_VERTICES);
			int destination = -1;
			while(true){
				destination = randomGenerator.nextInt(NUMBER_OF_VERTICES);;
				if(source != destination)
					break;
			}
			MaxCapacityPathAlgo1.executeDijkstra(graph, source, destination);
			MaxCapacityPathAlgo2.executeDijkstra(graph, source, destination);
			MaxCapacityPathAlgo3.executeAlgorithm(graph, source, destination);
			
			DirectedGraphAPI graph1 = GraphGenerator.generateDirectedGraph(NUMBER_OF_VERTICES);
			MaxCapacityPathAlgo2.executeDijkstra(graph1, source, destination);
		}
				
	}
}
