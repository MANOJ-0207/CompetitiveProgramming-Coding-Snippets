package WeightedGraphs;

import java.util.TreeSet;

public class DetailedEdge implements Comparable<DetailedEdge> {
	int source;
	int dest;
	int distance;
	DetailedEdge(int source, int dest, int distance)
	{
		this.source = source;
		this.dest = dest;
		this.distance = distance;
	}
	@Override
	public int compareTo(DetailedEdge that) {
		int distanceComparison =  Integer.compare(this.distance, that.distance);
		if(distanceComparison == 0)
		{
			int destComparison =  Integer.compare(this.dest,  that.dest);
			if(destComparison == 0)
				Integer.compare(this.source,  that.source);
			return destComparison;
		}
			
		return distanceComparison;
	}
	
	
	
	@Override
	public String toString() {
		return "DetailedEdge [source=" + source + ", dest=" + dest + ", distance=" + distance + "]";
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
	
}
