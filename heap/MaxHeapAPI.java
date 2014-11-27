package heap;

import graph.Edge;

public class MaxHeapAPI {

	private static Edge[] heap;
	private static int heapCount = 0;
	
	public MaxHeapAPI(int edgeCount){
		 heap = new Edge[edgeCount+1];
	}
	
	public static int getHeapCount(){
		return heapCount;
	}
	
	public static boolean isEmpty(){
		if(heapCount == 0){
			return true;	
		}else{
			return false;
		}
	}
	
	public static Edge[] getHeap() {
		return heap;
	}

	public static void setHeap(Edge[] heap) {
		MaxHeapAPI.heap = heap;
	}
	
	public static boolean exists(Edge edge){
		for(int i=1; i<=heapCount; i++){
			int v1 = edge.getOneVertex();
			int w1 = edge.getOtherVertex(v1);
			int v2 = heap[i].getOneVertex();
			int w2 = heap[i].getOtherVertex(v2);
			if((v1 == v2 && w1 == w2) || (v1 == w2 && w1 == v2)){
//				System.out.println("(" + v1 + "," + w2 + ") (" + v2 + "," + w2 + ")");
				return true;
			}
		}
		return false;
	}
	
	public static void insert(Edge edge){
		heapCount++;
		heap[heapCount] = edge;
		shiftUp(heapCount);
	}
	
	public static Edge maximum() throws Exception{
		if(heapCount == 0) throw new Exception("The heap is empty");
		return heap[1];
	}
	
	public static void delete(int index){
		heap[index] = heap[heapCount];
		heap[heapCount] = null;
		heapCount--;
		heapify(index);
	}	

	private static void shiftUp(int index) {	
		while(index > 1){
			int parent = index/2;
			if(heap[parent].getWeight() < heap[index].getWeight()){
				Edge temp = heap[index];
				heap[index] = heap[parent];
				heap[parent] = temp;
				index = parent;
			}else{
				break;
			}
		}
	}
	
	private static void heapify(int index) {
		int left = 2*index;
		int right = 2*index + 1;
		int largest;
		if(left < heapCount && heap[left].getWeight() > heap[index].getWeight()){
			largest = left;
		}else{
			largest = index;
		}
		if(right < heapCount && heap[right].getWeight() > heap[largest].getWeight()){
			largest = right;
		}
		if(largest != index){
			Edge temp = heap[index];
			heap[index] = heap[largest];
			heap[largest] = temp;
			heapify(largest);
		}
	}
	
	public static void main(String[] args) {
		Edge edge1 = new Edge(0, 1, 1);
		Edge edge2 = new Edge(1, 2, 17);
		Edge edge3 = new Edge(2, 3, 36);
		Edge edge4 = new Edge(3, 4, 2);
		Edge edge5 = new Edge(4, 0, 3);
		MaxHeapAPI api = new MaxHeapAPI(5);
		api.insert(edge1);
		api.insert(edge2);
		api.insert(edge3);
		api.insert(edge4);
		api.insert(edge5);
	}
}
