
public class BinSearchIntSet implements IntSet {

	public int size;// Gives us the number of elements we've added to our array
	public int[] set;
	
	public void add(int element) {
		// Only add if set doesn't contain element
		if(!contains(element)) {

			//If set is full, copy over elements to an array with double the size
			//Helper function doubleArray
			if(set.length == size) {
				doubleArray();
			}

			// If set is empty add element
			if(size==0) {
				set[size] = element;

			}else {
				//Find position to add element
				int indexAdd = size;
				while(indexAdd >=1) {
					if(element < set[indexAdd-1]){
						indexAdd--;
					}else {
						break;
					}
				}
				//Shift array to add element
				for(int j=size; j>=indexAdd+1;j--) {
					set[j] = set[j-1];
					}

				// Add element to shifted position
				set[indexAdd]=element;
			}
			size++;
		}
	}

	public boolean contains(int element) {
		//We start by assuming that list does not contain element
		//If we don't find an element we will return false
		Boolean result = false;

		if(size != 0) {		//Check if list is empty
							// BInary search for element in set
			int lower = 0;
			int upper = size-1;
			while(lower <= upper) {
				int mid = (upper-lower)/2+lower;
				if(element == set[mid]){
					result = true;
				}else if(element >= set[mid]) {
					lower = mid+1;
				}else {
					upper = mid-1;
				}
			}
		}
		return result;
	}

	// Helper function, doubles set size
	private void doubleArray(){
		int[] tempset = new int[(2*set.length)];
		for(int i =0; i < set.length;i++) {
			tempset[i] = set[i];
		}
		set = tempset;
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
	public BinSearchIntSet() {
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