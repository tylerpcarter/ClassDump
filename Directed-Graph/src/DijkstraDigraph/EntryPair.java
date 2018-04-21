package DijkstraDigraph;

public class EntryPair implements EntryPair_Interface {
	//Class for the EntryPair objects that can be entered into the heap.
	//Contains a string for value and a long for priority (priority determines 
	//the elements in the heap).
	  public String value;
	  public long priority;

	  public EntryPair(String aValue, long aPriority) {
	    value = aValue;
	    priority = aPriority;
	  }

	  public String getValue() { return value; }
	  public long getPriority() { return priority; }
}
