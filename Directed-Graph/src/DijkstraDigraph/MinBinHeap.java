package DijkstraDigraph;

public class MinBinHeap implements Heap_Interface {
	  private EntryPair[] array; //load this array
	  private int size;
	  private static final int arraySize = 10000; 

	  public MinBinHeap() {
	    this.array = new EntryPair[arraySize];
	    array[0] = new EntryPair(null, -100000); //0th will be unused for simplicity 
	                                             //of child/parent computations.
	                                             
	  }
	    
	  @Override
	  public EntryPair[] getHeap() { 
	    return this.array;
	  }

	  //Method for inserting items into the heap.
	  //Takes in an EntryPair object.
	@Override
	public void insert(EntryPair entry) {
		//Heap is empty, so insert into the first index.
		if(array[1]==null){
			array[1] = entry;
			size++;
		}
		else{
			//insertion is originally placed at the very end of the array, and is initially the last element of the heap 
			int hole = size + 1;
			//size of the heap is incremented by one
			size++;
			//element is inserted into the heap
			array[hole] = entry;
			
			boolean run = true;
			do{
			//checks the parent of the insertion (floor of the element at:(index of insertion)/2)
			//if the priority of the parent element is greater than (numerically)
			//the priority of the insertion, the elements are swapped and hole is incremented so the insertion's
			//new parent can be checked
				if(array[hole/2].priority>entry.priority && hole/2 != 0){
					array[hole] = array[hole/2];
					array[hole/2] = entry;
					hole/=2;
				}
				else{
					run = false;
				}
			}while(run == true);
		}
	}
	//method to delete elements from the heap
	//The first element in the heap is replaced with the last and then
	//moved back to it's appropriate place in the heap. 
	@Override
	public void delMin() {
		//last element of the heap replaces the first
		array[1] = array[size];
		//delete the duplicate
		array[size] = null;
		int child=1;
		int hole = 1;
		//subtract one from size due to the deletion
		size--;
		boolean run = true;
		do{
			child=child*2;
			hole = child;
			//compares both children of the element and increases child (if necessary)
			//to refer to the child that has the lower priority (numerically) 
			if(child < size && array[child+1].priority < array[child].priority){
				child++;
			}
			//Compares the element that was initially put at the top of the heap to
			//its child with the lowest priority (numerically)
			//and swaps them if the child has a lower numerical priority
			if(child <= size && array[hole/2].priority>array[child].priority){
				EntryPair temp = array[child/2];
				array[child/2] = array[child];
				array[child] = temp;
				
			}
			else{
				run = false;
			}
		
		}while(run == true);

	}
	//Method to return the minimum value in the heap (entry with lowest numerical priority)
	@Override
	public EntryPair getMin() {
	if(size==0){
		return null;
	}
	else{
		//If the heap is not empty, minimum value will be at the top. 
		return array[1];
	}
	}
	//method to return the size of the heap
	@Override
	public int size() {
		return size;
	}
	//Method to build a heap when given an array of values.
	@Override
	public void build(EntryPair[] entries) {
		size = entries.length;
		//Initially puts all values into the heap in the order they are given in.
		for(int j = 0; j<entries.length; j++){
			array[j+1] = entries[j];
		}
		//Traverses through each level of the heap starts the process of 
		//percolating the elements into their proper orders. 
		for(int i=size/2; i>0; i--){
			percolate(i);
		}
		
	}

	//Method to percolate elements into heap order, takes in an integer corresponing to the
	//starting point of the percolation.
	public void percolate(int hole){
		int child;
		EntryPair temp = array[hole];
		//checks the children of the element corresponding to the integer passed in
		//and swaps them with if the children have a lower priority (numerically)
		//after the swap this process continues (checking the new children each time
		//accessed by multiplying hole by 2) until all children are in proper heap order.  
		for( ; hole*2 <= size; hole = child){
			child = hole*2;
			if(child != size && array[child+1].priority<array[child].priority){
				child++;
			}
			if(array[child].priority<temp.priority){
				array[hole] = array[child];
			}
			else{
				break;
			}
		}
		array[hole] = temp;
		
	}


	}
