import java.util.ArrayList;

public class BinSearchGenSet<E extends Comparable<? super E>> implements GenSet<E> {
    public ArrayList <E> set;



    public void add(E element){
        if(set.size()==0){
            set.add(element);
        }else{
            for(int i = set.size()-1; i >= -1; i--){
                if(i == -1){
                    set.add(0,element);
                    break;
                }
                int result = (set.get(i)).compareTo(element);
                if(result < 0) {
                    set.add(i + 1, element);
                    System.out.println(element);
                    break;
                }
            }

        }

    }

    public boolean contains(E element) {
        if(set.size() == 0) {
            return false;
        }else {
            int lower = 0;
            int upper = set.size()-1;
            while(lower <= upper) {
                int mid = (upper-lower)/2+lower;
                if(element.equals(set.get(mid))){
                    return true;
                }else if(element.compareTo(set.get(mid))>0) {
                    lower = mid+1;
                }else {
                    upper = mid-1;
                }
            }
            return false;
        }
    }

    public void remove(E element){
        set.remove(element);
    }

    public BinSearchGenSet() {
        set = new ArrayList<E>();
    }

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
}