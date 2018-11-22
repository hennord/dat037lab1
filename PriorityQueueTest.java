import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class PriorityQueueTest<E> {
	private Comparator<E> comp;
	private ArrayList<E> heap = new ArrayList<E>();
	private HashMap<E,Integer> map = new HashMap<E,Integer>();
	
	public PriorityQueueTest(Comparator<E> comp) {
		this.comp = comp;
	}
		
	public void removeSmallest() {
		if (size() == 0) {
			throw new NoSuchElementException();
		}
		heap.set(0, heap.get(heap.size()-1));
		heap.remove(heap.size()-1);
		
		//preserves the invariant
		if (heap.size() > 0) siftDown(0);
	}
	
	public E getSmallest() {
		
		//check if there are any elements in the queue
		if(heap.size() == 0) {
			throw new NoSuchElementException();
		}
		//return first element in queue
		return heap.get(0);
	}
	
	public void add(E x) {
		assert invariant() : showHeap();
			heap.add(x);
			map.put(x,heap.size()-1);
			// preserves the invariant
			siftUp((heap.size()-1));
		assert invariant() : showHeap();
	}
	
	//Replaces an element in the queue with a new element
	public void update(E oldE, E newE) {
		int index = map.get(oldE);
		if(index != -1) {
			heap.set(index, heap.get(0));
			siftUp(index);
			heap.set(0, newE);
			siftDown(0);
		}
	}
	
	public int size() {
		return heap.size();
	}
	
	private void siftUp(int index) {
		E value = heap.get(index);
		map.remove(value,index);
		while(parent(index) >= 0) {
			int parent = parent(index);
			E parentValue = heap.get(parent);
			if(comp.compare(value, parentValue) < 0) {
				
				heap.set(index, parentValue);
				map.remove(parentValue,parent);
				map.put(parentValue,index);
				index = parent;
			} else break;
			
			if(index == 0) {
				break;
			}
		}
		heap.set(index,value);
		map.put(value,index);
	}
	
	private void siftDown(int index) {
		E value = heap.get(index);
		map.remove(value,index);
		while(leftChild(index) < heap.size()) {
			int left = leftChild(index);
			int right = rightChild(index);
			int child = left;
			E childValue = heap.get(left);
			
			if(right < size()) {
				E rightValue = heap.get(right);
				if(comp.compare(childValue,rightValue)>0) {
					child = right;
					childValue = rightValue;
				}
			}
			
			if(comp.compare(value, childValue) > 0 ) {
				heap.set(index, childValue);
				map.remove(childValue,child);
				map.put(childValue,index);
				index = child;
			} else break;
		}
		
		heap.set(index, value);
		map.put(value,index);
	}

	private int leftChild(int index) {
		return 2*index + 1;
	}
	
	private int rightChild(int index) {
		return 2*index+2;
	}
	
	private int parent(int index) {
		return (index-1)/2;
	}
	
	private boolean invariant(){
		boolean preserved = true;
		int parent = 0;
		while(preserved) {
			if(heap.size() == 0 || parent == heap.size()) break;
			/*if(heap.get(parent) == null) {
				preserved = false; break;
				}*/
			E parentValue = heap.get(parent);
			if(parent != (map.get(parentValue))) {
				preserved = false;
				break;
			};
			if(leftChild(parent)<heap.size()) {
				E leftValue = heap.get(leftChild(parent));
				if(rightChild(parent)<heap.size()) {
					E rightValue = heap.get(rightChild(parent));
					preserved = comp.compare(parentValue,leftValue)<=0 &&
								comp.compare(parentValue,rightValue)<=0;
				}else {
					preserved = comp.compare(parentValue,leftValue)<=0;
				}
			}		
			parent++;
		}
		return preserved;
	}

	private String showHeap(){
		int parent = 0;
		int left = leftChild(parent);
		int right = rightChild(parent);
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		while(left < heap.size()) {
			sb.append(heap.get(parent).toString() + " parent of " 
						+ heap.get(left).toString());
			if(right < heap.size()) {
				sb.append(" and " + heap.get(right).toString());
			}
			sb.append("\n");
			parent++;
			left = leftChild(parent);
			right = rightChild(parent);
		}
		return sb.toString();
	}
}