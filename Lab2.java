
import java.io.*;
import java.util.*;

public class Lab2 {
	public static String pureMain(String[] commands) {
		//DONE TODO: declaration of two priority queues 
		PriorityQueue<Bid> buyOrders = new PriorityQueue<Bid>(new BuyerComparator());
		
		PriorityQueue<Bid> sellOrders = new PriorityQueue<Bid>(new SellerComparator());

		StringBuilder sb = new StringBuilder();

		for(int line_no=0;line_no<commands.length;line_no++){
			String line = commands[line_no];
			if( line.equals("") )continue;

			String[] parts = line.split("\\s+");
			if( parts.length != 3 && parts.length != 4)
				throw new RuntimeException("line " + line_no + ": " + parts.length + " words");
			String name = parts[0];
			if( name.charAt(0) == '\0' )
				throw new RuntimeException("line " + line_no + ": invalid name");
			String action = parts[1];
			int price;
			try {
				price = Integer.parseInt(parts[2]);
			} catch(NumberFormatException e){
				throw new RuntimeException(
						"line " + line_no + ": invalid price");
			}
			int newPrice = 0;
			boolean wantUpdate = false;
			if(parts.length == 4) {
				try{
					newPrice = Integer.parseInt(parts[3]);
					wantUpdate = true;
				} catch(NumberFormatException e) {
					throw new RuntimeException(
					"line " + line_no + ": invalid new price");
				}
			}

			if( action.equals("K") ) {
				buyOrders.add(new Bid(name,price));
			} else if( action.equals("S") ) {
				sellOrders.add(new Bid(name,price));
			} else if( action.equals("NK") && wantUpdate){
					buyOrders.update(new Bid(name,price),new Bid(name,newPrice));
			} else if( action.equals("NS") && wantUpdate){
				sellOrders.update(new Bid(name,price),new Bid(name,newPrice));
			} else {
				throw new RuntimeException(
						"line " + line_no + ": invalid action");
			}


			// TODO:
			// compare the bids of highest priority from each of
			// each priority queues.
			// if the lowest seller price is lower than or equal to
			// the highest buyer price, then remove one bid from
			// each priority queue and add a description of the
			// transaction to the output.
			
			if(buyOrders.size() == 0 || sellOrders.size() == 0)continue;
			while(buyOrders.getSmallest().bid >= sellOrders.getSmallest().bid) {
				sb.append(buyOrders.getSmallest().name + " buys from " +
						sellOrders.getSmallest().name + " for " + sellOrders.getSmallest().bid + "\n");
				buyOrders.removeSmallest();
				sellOrders.removeSmallest();
				
				if(buyOrders.size() == 0 || sellOrders.size()== 0)break;
			}
		}

		sb.append("\n Order book:\n");

		sb.append("Sellers: ");
		// TODO: print remaining sellers.
		//       can remove from priority queue until it is empty.
		while(sellOrders.size() != 0) {
			sb.append(sellOrders.getSmallest().toString()+ ", ");
			sellOrders.removeSmallest();
		}
		sb.append("\n Buyers: ");
		// TODO: print remaining buyers
		//       can remove from priority queue until it is empty.
		while(buyOrders.size() != 0 ) {
			sb.append(buyOrders.getSmallest().toString() + ", ");
			buyOrders.removeSmallest();
		}
		return sb.toString();
	}

	public static void main(String[] args) throws IOException {
		final BufferedReader actions;
		if( args.length != 1 ){
			actions = new BufferedReader(new InputStreamReader(System.in));
		} else {
			actions = new BufferedReader(new FileReader(args[0]));
		}

		List<String> lines = new LinkedList<String>();
		while(true){
			String line = actions.readLine();
			if( line == null)break;
			lines.add(line);
		}
		actions.close();

		System.out.println(pureMain(lines.toArray(new String[lines.size()])));
	}
}