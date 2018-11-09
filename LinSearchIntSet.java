
public class LinSearchIntSet implements IntSet {
	public int size;
	public int[] set;
	
	public void add(int element) {
		if(!contains(element)) {
			if(set.length-1 >= size) {
					set[size] = element;
					size++;
			}else{
				int[] tempset = new int[(2*set.length)];
				for(int i =0; i < set.length;i++) {
					tempset[i] = set[i];
				}
					tempset[size] = element;
					size++;
					set = tempset;
			}
		}
	}
	
	public boolean contains(int element) {
		if(size == 0) {
			return false;
		}else {
			for(int x = 0; x < size; x++) {
				if(element == set[x]){
					return true;
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
	
	public LinSearchIntSet() {
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