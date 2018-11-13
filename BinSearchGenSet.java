import java.util.ArrayList;

public class BinSearchGenSet<E extends Comparable<? super E>> implements GenSet<E> {
    public ArrayList <E> set;



    public void add(E element){
        if(set.size()==0){
            set.add(element);
        }else{
            //Look through elements in set from largest through smallest
            for(int i = set.size()-1; i >= -1; i--){
                // If we've looked through the entire list add the element to the first position
                if(i == -1){
                    set.add(0,element);
                    break;

                // otherwise compare the element in set to the element we are trying to add
                }else if((set.get(i)).compareTo(element) < 0) {
                    set.add(i + 1, element);
                    break;
                }
            }

        }

    }

    public boolean contains(E element) {
        //We start by assuming that list does not contain element
        //If we don't find an element we will return false
        Boolean result = false;

        // If list is empty it doesn't contain anything
        if(set.size() != 0) {

            // Binary search to find element
            int lower = 0;
            int upper = set.size()-1;
            while(lower <= upper) {
                int mid = (upper-lower)/2+lower;
                if(element.equals(set.get(mid))){
                    result = true;
                }else if(element.compareTo(set.get(mid))>0) {
                    lower = mid+1;
                }else {
                    upper = mid-1;
                }
            }
        }
        return result;
    }

    public void remove(E element){
        set.remove(element);
    }

    //Constructor
    public BinSearchGenSet() {
        set = new ArrayList<E>();
    }

    // We use this for testing and debugging the code..
    /*
    public String toString(){
        String s = "[";
        if(set.size() == 0) {
            s += "]";
            return s;
        }
        for(int i = 0; i < set.size()-1; i++) {
            s = s + set.get(i) + ",";
        }
        s += set.get(set.size()-1) + "]";
        return s;
    }
    */
}