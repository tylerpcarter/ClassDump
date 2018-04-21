package DijkstraDigraph;

public class DiGraphPlayground {
	//Run this class to see the program work.
	public static void main(String[]args){
		exTest();
	}
	public static void exTest(){
		DiGraph d=new DiGraph();
		String cities[]={"Raleigh", "Durham", "Chapel_hill","Carborro","Pittsboro",
				"Sanford","Los_angeles","Graham","Hillsborough","Cary"};
		int i=1;
		for(String city:cities){
			d.addNode(i, city);
			i++;
		}
		d.addEdge(0, "Raleigh", "Durham", 14, "Rale->Durh");
		d.addEdge(1, "Durham", "Hillsborough", 9, "Durh->Hill");
		d.addEdge(2, "Chapel_hill", "Graham", 25, "Chap->Grah");
		d.addEdge(3, "Chapel_hill", "Carborro", 1, "Chap->Carr");
		d.addEdge(4, "Carborro", "Cary", 32, "Carr->Cary");
		d.addEdge(5, "Cary", "Raleigh", 3, "Cary->Rale");
		d.addEdge(6, "Pittsboro", "Cary", 17, "Pitt->Cary");
		d.addEdge(7, "Pittsboro", "Sanford", 15, "Pitt->Sanf");
		d.addEdge(8, "Sanford", "Los_angeles", 3012, "Sanf->Los");
	   	
		d.shortestPath("Pittsboro");
		System.out.println("numEdges: "+d.numEdges());
	    System.out.println("numNodes: "+d.numNodes());
	    printTOPO(d.topoSort());
	    System.out.println("-1 means no path");
	   	for(Vertex verts:d.vertList){
	   		System.out.println(d.vertices.get(verts.idNum)+"      "+d.vertObjs.get(verts.label).distance);
	   	}    	
	}
	 public static void printTOPO(String[] toPrint){
	      System.out.print("TOPO Sort: ");
	      for (String string : toPrint) {
	      System.out.print(string+" ");
	    }
	      System.out.println();
	    }

}