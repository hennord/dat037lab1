
public class BinSearchIntSet implements IntSet {
	public int size;
	public int[] set;
	
	public void add(int element) {
		int indexAdd;
		if(!contains(element)) {
			if(set.length == size) {
				int[] tempset = new int[(2*set.length)];
				for(int i =0; i < set.length;i++) {
					tempset[i] = set[i];
				}
				set = tempset;
			}
			if(size==0) {
				set[size] = element;
				size++;
			}else {
				indexAdd = size;
				while(indexAdd >=1) {
					if(element < set[indexAdd-1]){
						indexAdd--;
					}else {
						break;
					}
				}
				for(int j=size; j>=indexAdd+1;j--) {
					set[j] = set[j-1];
					}
				set[indexAdd]=element;
				size++;	
			}
		}
	}

	public boolean contains(int element) {
		if(size == 0) {
			return false;
		}else {
			int lower = 0;
			int upper = size-1;
			while(lower <= upper) {
				int mid = (upper-lower)/2+lower;
				if(element == set[mid]){
					return true;
				}else if(element >= set[mid]) {
					lower = mid+1;
				}else {
					upper = mid-1;
				}
			}
			return false;
		}
	}
	
	private int indexOf(int element){
		for(int i=0; i<size;i++) {
			if(set[i] == element) {
				return i;
			}	
		}
		return -1;
	}
	
	public void remove(int element) {
		int index = indexOf(element);
		if(index == -1) {
			//System.out.print("\n"+"Set does not contain this element.");
		}else {
			for(int i = index; i < size-1; i++) {
				set[i] = set[i+1];
			}
		set[size-1]=0;
		size -=1;
		}
		
	}
	
	public BinSearchIntSet() {
		size = 0;
		set = new int[1];
	}
	
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
}