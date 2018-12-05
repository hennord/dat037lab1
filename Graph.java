package dat037lab3;

import java.util.*;

public class Graph {
	private ArrayList<String> vertexes;
	private List<HashSet<weightedEdge>> edges;
    
    public Graph() { 
    	vertexes = new ArrayList<>();
    	edges = new LinkedList<>();
    }
    
    public void addVertex(String label) {
    	vertexes.add(label);
    	edges.add(new HashSet<weightedEdge>());
    }
    
    //Adds a new edge to the graph, cannot update already existing edges.
    public void addEdge(String node1, String node2, int dist) {
    	if(!(vertexes.contains(node1) || vertexes.contains(node2))) {
    		System.out.println("Atleast one of these nodes are not in the graph.");
    		return;
    	}
    	if(edges.get(vertexes.indexOf(node1)).contains(new weightedEdge(node1,node2,dist))) {
    		System.out.println("Graph already has this edge");
    		return;
    	}
    	edges.get(vertexes.indexOf(node1)).add(new weightedEdge(node1,node2,dist));
    	edges.get(vertexes.indexOf(node2)).add(new weightedEdge(node2,node1,dist));
    }
    
    public static class Path {
        public int totalDist;
        public List<String> vertices;
        public Path(int totalDist, List<String> vertices) {
            this.totalDist = totalDist;
            this.vertices = vertices;
        }

        @Override
        public String toString() {
            return "totalDist: " + totalDist + ", vertices: " + vertices.toString();
        }
    }
    
   public Path shortestPath(String start, String dest) {
	   //Create priority queue of border edges, weightComparator compares distance between weightedEdges.
	   PriorityQueue<weightedEdge> borderQueue = new PriorityQueue<>(new weightComparator());
	   ArrayList<String> visitedNodes = new ArrayList<>();
	   ArrayList<weightedEdge> travelledEdges = new ArrayList<>();
	   
	   //Add edges from the start node to the priority queue. 
	   for(weightedEdge w : edges.get(vertexes.indexOf(start))) {
		   borderQueue.add(w);
	   }
	   visitedNodes.add(start);
	   String currentNode = start;
	   weightedEdge recentAddedEdge = borderQueue.getSmallest();
	   
	   //Loop while we haven't added the destination node and there are new nodes to add.
	   while(!visitedNodes.contains(dest) && borderQueue.size() != 0) {
		   recentAddedEdge = borderQueue.getSmallest();
		   travelledEdges.add(recentAddedEdge);
		   currentNode = recentAddedEdge.getEnd();
		   //System.out.println(currentNode);
		   visitedNodes.add(currentNode);
		   borderQueue.removeSmallest();
		   for(weightedEdge w : edges.get(vertexes.indexOf(currentNode))) {
			   //Doesen't add edges to nodes we have already visited. 
			   if(!visitedNodes.contains(w.getEnd())) {
				   borderQueue.add(w.combine(recentAddedEdge));
			   }
		   }
	   }
	   //return null if the destination couldn't be found.
	   if(!visitedNodes.contains(dest)) {
		   return null;
	   }
	   
	   List<String> vertices = new ArrayList<>();
	   //The final edge added has the weight of the total path
	   int totalDist = recentAddedEdge.getWeight();
	   vertices.add(currentNode);
	   while(!vertices.contains(start)) {
		   for(weightedEdge w: travelledEdges) {
			   if(w.getEnd().equals(currentNode)) {
				   currentNode = w.getStart();
				   vertices.add(currentNode);
				   break;
			   }
		   }   
	   }
	   Collections.reverse(vertices);
	   Path result = new Path(totalDist,vertices);
	   return result;
   }
    
    private class weightedEdge{
    	
    	private int weight;
    	private String end;
    	private String start;
    	
    	public weightedEdge(String start, String end, int weight) {
    		this.weight = weight;
    		this.end = end;
    		this.start = start;
    	}
    	
    	public String getEnd() {
    		return end;
    	}
    	
    	public String getStart() {
    		return start;
    	}
    	
    	public String toString() {
    		return start + " " + end + " " + weight;
    	}
    	
    	public int getWeight() {
    		return weight;
    	}
    	
    	//Combines two edges and returns the combined edge, the added edge e has to have the same start location
    	//as the end location of this edge. 
    	public weightedEdge combine(weightedEdge e) {
    		/*if(!e.getStart().equals(end)) {
    			throw new IllegalArgumentException();
    		}*/
    		return new weightedEdge(start,end,weight+e.getWeight());
    	}
    	
    	@Override
    	public boolean equals(Object o) {
    		if(!(o instanceof weightedEdge)) {
    			return false;
    		}
    		
    		weightedEdge e = (weightedEdge) o;
    		if(this.end.equals(e.getEnd())) {
    			return true;
    		}
    		return false;
    	}
    	
    	//Used by HashSet to see if graph already has this edge.
    	//Only checks the end destination, cannot update already existing edges with new
    	//weights using addEdge().
    	@Override
        public int hashCode() {
            return end.hashCode();
        }
    }
    
    private class weightComparator implements Comparator<weightedEdge>{
    	@Override
    	public int compare(weightedEdge e1, weightedEdge e2) {
    		if(e1.getWeight() == e2.getWeight()) {
    			return 0;
    		}else if(e1.getWeight() < e2.getWeight()){
    			return -1;
    		}
    		return 1;
    	}
    }
}
