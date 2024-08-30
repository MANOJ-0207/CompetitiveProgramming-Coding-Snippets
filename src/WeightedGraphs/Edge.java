package WeightedGraphs;

import java.util.Objects;
import java.util.TreeSet;

public class Edge implements Comparable<Edge> 
{
	int dest;
	int distance;
	Edge(int dest, int distance)
	{
		this.dest = dest;
		this.distance = distance;
	}
	@Override
	public int compareTo(Edge that) {
		int distanceComparison =  Integer.compare(this.distance, that.distance);
		if(distanceComparison == 0)
				return Integer.compare(this.dest,  that.dest);
		return distanceComparison;
	}
	
	
	@Override
	public String toString() {
		return "Edge [dest=" + dest + ", distance=" + distance + "]";
	}
	
//	 @Override
//	    public boolean equals(Object o) {
//	        if (this == o) 
//	        	return true;
//	        if (o == null || getClass() != o.getClass()) 
//	        	return false;
//	        Edge that = (Edge)o;
//	        return (dest == that.dest) && (distance == that.distance);
//	    }
//	 
//	 @Override
//	 public int hashCode() {
//	        return Objects.hash(dest, distance);
//	 }
	public static void main(String[] args) 
	{
		var s = new TreeSet<Edge>();
		s.add(new Edge(3,4));
		s.add(new Edge(6,2));
		s.add(new Edge(5,3));
		s.add(new Edge(1,10));
		s.add(new Edge(2,8));
		for(Edge e : s)
			System.out.println(e);
		s.remove(new Edge(5,3));
		System.out.println("========================");
		for(Edge e : s)
			System.out.println(e);
	}
}


