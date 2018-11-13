import com.sun.org.apache.xpath.internal.operations.Bool;

public class LinSearchIntSet implements IntSet {

	public int size;// Gives us the number of elements we've added to our array
	public int[] set;
	
	public void add(int element) {
		// Only add elements if they'r not in set
		if(!contains(element)) {

			// If our array is full, copy over to an array twice as big
			if(!(set.length-1 >= size)) {
				int[] tempset = new int[(2*set.length)];
				for(int i =0; i < set.length;i++) {
					tempset[i] = set[i];
				}
					set = tempset;
			}

			//Add new element
			set[size] = element;
			size++;
		}
	}
	
	public boolean contains(int element) {
		//We start by assuming that list does not contain element
		//If we don't find an element we will return false
		Boolean result = false;

		// If list is empty it doesn't contain anything
		if(size != 0) {

			//Linear search for element in set
			for(int x = 0; x < size; x++) {
				if(element == set[x]){
					result = true;
				}
			}
		}
		return result;
	}

	public void remove(int element) {
		int index = indexOf(element);// Find index of element
		if(index != -1){			// Check if list contain element

			//Shifts elements in list
			for(int i = index; i < size-1; i++) {
				set[i] = set[i+1];
			}
		// Set last element of previously filled list to 0 (null in array)
		// and decrease size of filled array
		set[size-1]=0;
		size -=1;
		}
		
	}

	//Helper function to remove
	//Finds the index of an element in a list, if element not found, return -1
	private int indexOf(int element){
		int result = -1;
		for(int i=0; i<size;i++) {
			if(set[i] == element) {
				result = i;
			}
		}
		return result;
	}

	//Constructor
	public LinSearchIntSet() {
		size = 0;
		set = new int[1];
	}

	// We use this for testing and debugging the code..
	/*
	public String toString(){
		String s = "[";
		if(size == 0) {
			s += "]";
			return s;
		}
		for(int i = 0; i < size-1; i++) {
			s = s + set[i] + ",";
		}
		s += set[size-1] + "]";
		return s;
	}
	*/
}
