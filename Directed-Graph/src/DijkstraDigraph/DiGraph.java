package DijkstraDigraph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.ArrayList; // Implementation

public class DiGraph implements DiGraph_Interface {

	public DiGraph () { 

	}
 
	private HashSet<Long> edgeIDs = new HashSet<Long>(); 
	
	Map<Long, String> mapEdges = new HashMap<Long, String>();
	Map<Long, String> vertices = new HashMap<Long, String>();
	Map<String, Vertex> vertObjs = new HashMap<String, Vertex>();
	Map<String, Edge> edgeObjs = new HashMap<String, Edge>();
	ArrayList<Vertex> vertList = new ArrayList<Vertex>(); 
	ArrayList<Edge> edgeList = new ArrayList<Edge>(); 
	public static final String ghost = "$$$";
	static final long MAX = 99999;


//Method for adding a vertex into the graph.  Takes in the id number and label associated with the vertex.
	@Override
	public boolean addNode(long idNum, String label) {  
		//not a valid vertex if id number is less than 0 or if it does not contain a label.
		if(idNum < 0 || label == null) {
			return false; 
		}
		//not a valid vertex if the vertex is already in the graph.  Checks hashmap for duplicates. 
		if(vertices.containsKey(idNum)||vertices.containsValue(label)) {
			return false; 
		}
		//Insertion is valid, vertex is added to the arrayList of vertices, the hashmap of strings and labels, and the hashmap
		//of vertex objects. 
		else{
			Vertex tempVert = new Vertex(idNum, label);
			vertList.add(tempVert);
			vertices.put(idNum, label);
			vertObjs.put(label, tempVert);
			return true; 
		}
	}


//Method for adding an edge to the graph.  Takes in idNumber; label, and weight of the edge.
//Also takes in labels corresponding to the source and destination vertices. 
	@Override
	public boolean addEdge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {	
		
		Edge insertion = new Edge (idNum, sLabel, dLabel, weight, eLabel);
		//not a valid edge if the id number is les than zero or if the edge already exists.
		if(idNum < 0 || mapEdges.containsKey(idNum)) {
			return false; 
		}
		//not a valid edge if the source or destination vertices do not exist.
		if(vertices.containsValue(dLabel) == false || vertices.containsValue(sLabel) == false) {
			return false; 
		}
		//not a valid edge if an edge already exists between the source and destination vertices. 
		if(edgeObjs.containsKey(sLabel + ghost +  dLabel)){
			return false;
		}
		//valid edge if above exceptions are not true, and if source or destination vertex are found
		//(second of the above if statements insures that both are valid and exist). 
		if(vertObjs.get(sLabel).label.equals(sLabel) || vertObjs.get(dLabel).label.equals(dLabel)) {
			Vertex temp = vertObjs.get(sLabel);
			Vertex temp1 = vertObjs.get(dLabel);
			//newly added edge's source and destination vertices are set to vertices corresponding to sLabel and dLabel
			//new added edge is added to the destination vertex's inEdges hashset and source vertex's outEdges hashset. 
			//the edge is also stored in the hashmap of edges, hashmap of edge objects, and arrayList of edges.
			insertion.destVertex = temp1;
			insertion.sourceVertex = temp;
			insertion.destVertex.inEdges.add(insertion);
			insertion.sourceVertex.outEdges.add(insertion);
			mapEdges.put(idNum, eLabel);
			edgeList.add(insertion);
			//Edge objects are stored with their key as a concatenation of the source vertex label and destination  vertex label.
			//This way an edge can be retrieved through it's source and destination vertexs.  The ghost string is added to handle when
			//one edges source vertex label is: "Si" with destination vertex "n" and another edge has source vertex label: "S" and 
			//destination vertex label: "in". 
			edgeObjs.put(sLabel + ghost + dLabel, insertion);

			return true;

		}
		return false;
	}

//Method to delete a vertex from the graph.  Takes in the label corresponding to the vertex that is to be deleted.
	@Override
	public boolean delNode(String label) {  
		//Not a valid deletion if the vertex does not exist. 
		if(vertices.containsValue(label) == false) {
			return false; 
		}
		else {
		//removes vertex from both hashmaps and the arrayList, saves a temporary so deletion's properties can be accessed.
			Vertex temp = vertObjs.get(label);
			vertices.remove(temp.idNum);
			vertList.remove(temp);
			vertObjs.remove(label);
		//loops through the deleted vertex's inEdges hashset and deletes all of those edges from the graph.
			for (Edge tempEdge : temp.inEdges) {
				tempEdge.sourceVertex.outEdges.remove(tempEdge);
				edgeIDs.remove(tempEdge.idNum);
				mapEdges.remove(tempEdge.idNum);
				edgeList.remove(tempEdge);
				edgeObjs.remove(tempEdge.sourceVertex.label + ghost + tempEdge.destVertex.label); 
			}
			//loops through the deleted vertex's outEdges hashset and deletes all of those edges from the graph.
			for (Edge tempEdge1 : temp.outEdges) {
				tempEdge1.destVertex.inEdges.remove(tempEdge1);  
				edgeIDs.remove(tempEdge1.idNum);
				mapEdges.remove(tempEdge1.idNum);
				edgeList.remove(tempEdge1);
				edgeObjs.remove(tempEdge1.sourceVertex.label + ghost + tempEdge1.destVertex.label); 
			}


			return true;
		}
	}



//Method to delete edges from the graph.  Takes in the labels of the source and destination vertices of the edge to be deleted.  
	@Override
	public boolean delEdge(String sLabel, String dLabel) {
		//If the edge exists, remove from all hashmaps and arrayList. 
		if(edgeObjs.containsKey(sLabel + ghost + dLabel)) {
			Edge tempEdge = edgeObjs.get(sLabel + ghost + dLabel);		
			tempEdge.sourceVertex.outEdges.remove(tempEdge);
			tempEdge.destVertex.inEdges.remove(tempEdge);
			mapEdges.remove(tempEdge.idNum);
			edgeList.remove(tempEdge.idNum);
			edgeObjs.remove(sLabel + ghost + dLabel);
			tempEdge.sLabel = null; 
			tempEdge.dLabel = null; 
			return true; 
		}
		return false; 
	}



//Method to get the number of vertices in the graph.
	@Override
	public long numNodes() {   
		return vertices.size();
	}

//Method to get the number of edges in the graph.  
	@Override
	public long numEdges() {
		return mapEdges.size();
	}

//Method that topologically sorts the vertices in the graph using Kahn's algorithm
	@Override
	public String[] topoSort() {
		String [] topoSorted = new String[vertList.size()]; 
		//List that will contain sorted vertices
		ArrayList<Vertex> vList = new ArrayList<Vertex>();
		//Set which will contain all of the vertices with no in edges. 
		HashSet<Vertex> vSet = new HashSet<Vertex>(); 
		//loops through all of the vertices and adds those with no in edges to vSet.
		for(Vertex vertTemp : vertList) {
			if (vertTemp.inEdges.size() == 0) {
				vSet.add(vertTemp);
			}
		}
		//while there are still vertices with no in edges this loop will run. 
		while(!vSet.isEmpty()) {
			Vertex vertTemp = vSet.iterator().next();
			//removes the Vertex from vSet
			vSet.remove(vertTemp);
			//adds the Vertex to vList
			vList.add(vertTemp); 
		//for each vertex with edge between the node you just deleted and itself, delete the edge. 
		//if the vertex has no other in edges than insert it into vSet
			for(Iterator<Edge> EOI = vertTemp.outEdges.iterator(); EOI.hasNext();) {
				Edge e = EOI.next();
				Vertex dest = e.destVertex; 
				EOI.remove();
				dest.inEdges.remove(e); 

				if(dest.inEdges.isEmpty()) {
					vSet.add(dest);
				}
			}
		}
		//If any of the vertices contain in edges, return null.
		//This will happen if the graph is not acyclic. 
		for(Vertex temp : vertList) {
			if (!temp.inEdges.isEmpty()) {
				return null;
			}
		}

		int index = 0;
		//Add sorted vertices to topoSorted array. 
		for(Vertex s : vList) {
			topoSorted[index] = s.label; 
			index++;
		}

		return topoSorted; 
	}


//Method for finding the shortest path from a given vertex to all
//other vertices in the graph.  Given a string corresponding to the label of the starting vertex.
	@Override
	public ShortestPathInfo[] shortestPath(String label) {
		MinBinHeap heap = new MinBinHeap();
		//The distance from a vertex to itself is 0.  
		vertObjs.get(label).distance = 0;
		//add the starting point vertex onto the heap. 
		heap.insert(new EntryPair(label, vertObjs.get(label).distance));
		//loops until the heap is empty
			while(heap.size() > 0){
				//retrieves the vertex with the least weight (shortest distance) from the heap
				Vertex temp = vertObjs.get(heap.getMin().value);
				//deletes the vertex from the heap
				heap.delMin();
				//sets the vertex's visited property to true 
				temp.visited = true;
		        //loops through the vertex's out edges
				for(Edge edge: temp.outEdges){
				//if the vertex at the end of the out edge has not been visited and has a weight greater than
				//distance from starting point + edge weight (all vertex's distance properties are originally set to infinity)
				//update the vertex's distance to its actual distance from the starting point and add it onto the heap. 
					if(edge.destVertex.distance>temp.distance + edge.weight && 
							edge.destVertex.visited == false){
						edge.destVertex.distance = (temp.distance + edge.weight);
						heap.insert(new EntryPair(edge.destVertex.label, edge.destVertex.distance));
						
					}
				}
			}
			ShortestPathInfo [] shortestPath = new ShortestPathInfo[vertList.size()];
			//adds the sorted vertex's to the shortestPath array.
			int counter = 0;
			for(Vertex vert: vertList){
				//this if statement is caught if a vertex was added to the graph with no edges connecting it 
				//to the rest of the graph.  It is unreachable from the any other node but will still be topo sorted 
				//because it has no in or out edges.  It's distance is set to -1 which signifies that it is unreachable. 
				if(vert.visited == false){
					vert.distance = -1;
				}
				ShortestPathInfo data = new ShortestPathInfo(vert.label, vert.distance);
				shortestPath[counter] = data;
				counter++;
			}
			
		return shortestPath;
	}

}