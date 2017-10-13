package DiGraph_A5;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.ArrayList; // Implementation

public class DiGraph implements DiGraph_Interface {

	// in here go all your data and methods for the graph
	// and the topo sort operation

	public DiGraph () { // default constructor
		// explicitly include this
		// we need to have the default constructor
		// if you then write others, this one will still be there


	}

	private HashSet<Long> vertex_ids = new HashSet<Long>(); 
	private HashSet<Long> edge_ids = new HashSet<Long>(); 


	Map<Long, String> edgeMap = new HashMap<Long, String>();
	Map<Long, String> vertMap = new HashMap<Long, String>();

	Map<String, Vertex> vertexComp = new HashMap<String, Vertex>(); ///this
	Map<String, Edge> edgeComp = new HashMap<String, Edge>(); ///this


	ArrayList<Vertex> vertList = new ArrayList<Vertex>(); 
	ArrayList<Edge> edgeList = new ArrayList<Edge>(); 




	@Override
	public boolean addNode(long idNum, String label) {  ///Nothing 
		if(idNum < 0) {
			return false; 
		}
		else if (label == null) {
			return false; 
		}
		if(vertMap.containsKey(idNum)||vertMap.containsValue(label)) {
			return false; 
		}
		else{
			Vertex vertexConObj = new Vertex(idNum, label);
			vertList.add(vertexConObj);
			vertMap.put(idNum, label);
			vertexComp.put(label, vertexConObj);
			return true; 
		}
	}



	@Override
	public boolean addEdge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {	

		Edge edgeVar = new Edge (idNum, sLabel, dLabel, weight, eLabel);
		if(idNum < 0) {
			return false; 
		}
		if(edgeMap.containsKey(idNum)){
			return false;
		}

		if(vertMap.containsValue(dLabel) == false || vertMap.containsValue(sLabel) == false) {
			return false; 
		}

		if(edgeComp.containsKey(sLabel + "filler" +  dLabel)){
			return false;
		}

		if(vertexComp.get(sLabel).label.equals(sLabel) || vertexComp.get(dLabel).label.equals(dLabel)) {
			edgeVar.destVert = vertexComp.get(dLabel);
			edgeVar.srcVert = vertexComp.get(sLabel);
			edgeVar.destVert.inEdges.add(edgeVar);
			edgeVar.srcVert.outEdges.add(edgeVar);


			edgeMap.put(idNum, eLabel);
			edgeList.add(edgeVar);
			edgeComp.put(sLabel + "filler" + dLabel, edgeVar);

			return true;

		}
		return false;
	}



	@Override
	public boolean delNode(String label) {  //NULL POINTER
		//	if (!vertexComp.containsKey(label)) {
		//		return false; 
		//	}
		//	Vertex vx = vertexComp.remove(label); 
		//	long id = vx.idNum; 
		//	vertex_ids.remove(id); 
		//	
		//	for(Edge e : vx.inEdges) {
		//		e.srcVert.outEdges.remove(e);
		//		edge_ids.remove(e.idNum);
		//	}
		//	vx.inEdges = null; 
		//	
		//	for(Edge e: vx.outEdges) {
		//		e.destVert.inEdges.remove(e);
		//		edge_ids.remove(e.idNum); 
		//	}
		//	vx.outEdges = null;
		//	return true; 

		if(vertMap.containsValue(label) == false) {
			return false; 
		}
		else {
			Vertex defCon = vertexComp.get(label);
			vertMap.remove(defCon.idNum);
			vertList.remove(defCon);
			vertexComp.remove(label);
			
			//vertex_ids.remove(defCon.idNum); //remove 

			for ( Edge superSpecial : defCon.inEdges) {
				superSpecial.srcVert.outEdges.remove(superSpecial);
				edge_ids.remove(superSpecial.idNum);
				edgeMap.remove(superSpecial.idNum);
				edgeList.remove(superSpecial);
				edgeComp.remove(superSpecial.srcVert.label + "filler" + superSpecial.destVert.label); //take out super
			}

			for ( Edge superSpecial : defCon.outEdges) {
				superSpecial.destVert.inEdges.remove(superSpecial);  //switch src
				edge_ids.remove(superSpecial.idNum);
				edgeMap.remove(superSpecial.idNum);
				edgeList.remove(superSpecial);
				edgeComp.remove(superSpecial.srcVert.label + "filler" + superSpecial.destVert.label); 
			}


			return true;
		}
	}




	@Override
	public boolean delEdge(String sLabel, String dLabel) {

		if(edgeComp.containsKey(sLabel + "filler" + dLabel)) {
			Edge defCon = edgeComp.get(sLabel + "filler" + dLabel);

			defCon.srcVert.outEdges.remove(defCon);
			defCon.destVert.inEdges.remove(defCon);
			edgeMap.remove(defCon.idNum);
			edgeList.remove(defCon.idNum);
			edgeComp.remove(sLabel + "filler" + dLabel);

			defCon.sLabel = null; 
			defCon.dLabel = null; 
			return true; 
		}
		return false; 
	}




	@Override
	public long numNodes() {  ///Nothing 
		return vertMap.size();
	}


	@Override
	public long numEdges() {
		return edgeMap.size();
	}




	@Override
	public String[] topoSort() {
		String [] SortedStrings = new String[vertList.size()]; 
		ArrayList<Vertex> L = new ArrayList<Vertex>();

		HashSet<Vertex> NodeSet = new HashSet<Vertex>(); 

		for(Vertex vertTemp : vertList) {
			if (vertTemp.inEdges.size() == 0) {
				NodeSet.add(vertTemp);
			}
		}

		while(!NodeSet.isEmpty()) {
			Vertex vertTemp = NodeSet.iterator().next();
			NodeSet.remove(vertTemp);

			L.add(vertTemp); 

			for(Iterator<Edge> EOI = vertTemp.outEdges.iterator(); EOI.hasNext();) {
				Edge e = EOI.next();
				Vertex dest = e.destVert; 
				EOI.remove(); // remove edge from n 
				dest.inEdges.remove(e); //remove edge from m 

				if(dest.inEdges.isEmpty()) {
					NodeSet.add(dest);
				}
			}
		}

		for(Vertex vertTemp : vertList) {
			if (!vertTemp.inEdges.isEmpty()) {
				return null;
			}
		}

		int i = 0;
		for(Vertex s : L) {
			SortedStrings[i] = s.label; 
			i++;
		}

		return SortedStrings; 
	}



	// rest of your code to implement the various operations
}