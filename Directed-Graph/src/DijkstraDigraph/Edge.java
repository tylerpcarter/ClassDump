package DijkstraDigraph;

public class Edge {
	public String sLabel; 
	public String dLabel; 
	public String eLabel; 
	public long weight; 
	public long idNum; 
	//Vertices corresponding to where the edge starts and ends
	public Vertex sourceVertex;
	public Vertex destVertex; 
	//Edge objects, has an identification number, string corresponding to the source vertex,
	//destination vertex string, string corresponding to the label of the edge, and a long for wieght. 
	public Edge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {
		this.idNum = idNum; 
		this.dLabel = dLabel; 
		this.sLabel = sLabel; 
		this.weight = weight; 
		this.eLabel = eLabel; 
	}
	
	
}