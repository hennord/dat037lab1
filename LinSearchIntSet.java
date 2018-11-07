
public class LinSearchIntSet implements IntSet {
	public int position;
	public int[] set;
	public boolean empty;
	
	public void add(int element) {
		empty = false;
		if(set[set.length-1] == 0) {
			if(!contains(element)) {
				set[position] = element;
				position++;
			}
		}else{
			int[] tempset = new int[(2*set.length)];
			for(int i =0; i < set.length;i++) {
				tempset[i] = set[i];
			}
			tempset[position] = element;
			position++;
			set = tempset;
		}
	}
	
	public boolean contains(int element) {
		if(empty) {
			return false;
		}else {
			for(int x = 0; x < set.length; x++) {
				if(set[x] == 0) {
					return false;
				}else if(element == set[x]){
					return true;
				}
			}
			return false;
		}
	}
	
	private int indexOf(int element){
		for(int i=0; i<set.length;i++) {
			if(set[i] == element) {
				return i;
			}	
		}
		return -1;
	}
	
	public void remove(int element) {
		int index = indexOf(element);
		if(index == -1) {
			System.out.print("\n"+"Set does not contain this element.");
		}else {
			for(int i = index; i < set.length-1; i++) {
				if(set[i] == 0) {
					break;
				}else {
					set[i] = set[i+1];
				}
			}
			set[set.length-1]=0;
			position -=1;
		}
	}
	
	public LinSearchIntSet() {
		position = 0;
		set = new int[1];
		empty = true;
	}
	
	public String toString(){
		String s = "[";
		for(int i = 0; i < set.length; i++) {
			s = s + set[i] + ",";
		}
		s += "]";
		return s;
	}
}
