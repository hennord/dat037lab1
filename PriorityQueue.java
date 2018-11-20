import java.util.*;

public class PriorityQueue<E> {
	private Comparator<E> comp;
	private ArrayList<E> heap = new ArrayList<E>();
	
	
	public PriorityQueue(Comparator<E> comp) {
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
		heap.add(x);
		
		// preserves the invariant
		siftUp((heap.size()-1));
	}
	
	//Replaces an element in the queue with a new element
	public void update(E oldE, E newE) {
		int index = heap.indexOf(oldE);
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
		while(parent(index) >= 0) {
			int parent = parent(index);
			//int child = index;
			E parentValue = heap.get(parent);
			if(comp.compare(value, parentValue) < 0) {
				heap.set(index, parentValue);
				index = parent;
			} else break;
			
			if(index == 0) {
				break;
			}
		}
		heap.set(index, value);
	}
	
	private void siftDown(int index) {
		E value = heap.get(index);
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
				index = child;
			} else break;
		}
		
		heap.set(index, value);
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
}
