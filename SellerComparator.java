import java.util.*;

public class SellerComparator implements Comparator<Bid>{
	/*compares two sell orders
	returns -1 if the first sell order is smaller than the second
	returns 1 if the first sell order is larger than the second
	returns 0 if the sell orders are equal*/
	@Override
	public int compare(Bid bid1, Bid bid2) {
		if(bid1.bid == bid2.bid) {
			return 0;
		}else if(bid1.bid < bid2.bid){
			return -1;
		}
		return 1;
	}
}
