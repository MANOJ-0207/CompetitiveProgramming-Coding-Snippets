package DisjointSetUnion;

import java.util.ArrayList;

public class DSUGraph {
	int n;
	ArrayList<Pair> list;
	int parent[];
	int rank[];
	public DSUGraph(int count)
    {
        this.n = count+1;
        this.list = new ArrayList<Pair>();
        this.parent = new int[n];
        this.rank = new int[n];
        for(int i=0; i<n; i++)
			parent[i] = -1;
    }
	
	void addEdge(int v1, int v2)
    {
        list.add(new Pair(v1,v2));
    }
	
	int findSet(int i)
	{
		if(parent[i] == -1)
			return i;
		return findSet(parent[i]);
	}
	
	public int findSetOptimized(int i)
	{
		if(parent[i] == -1)
			return i;
		parent[i] = findSetOptimized(parent[i]);
		return parent[i];
	}
	
	void unionSet(int x, int y)
	{
		int s1 = findSet(x);
		int s2 = findSet(y);
		
		if(s1 != s2)
			parent[s2] = s1;
	}
	
	public void unionSetOptimized(int x, int y)
	{
		int s1 = findSetOptimized(x);
		int s2 = findSetOptimized(y);
		
		if(s1 != s2) {
			if(rank[s1] < rank[s2])
			{
				parent[s1] = s2;
				rank[s2] += rank[s1];
			}
			else
			{
				parent[s2] = s1;
				rank[s1] += rank[s2];
			}
			parent[s2] = s1;
		}
	}
	
	boolean cycleCheck()
	{
		for(Pair p : list)
		{
			int i = p.v1;
			int j = p.v2;
			
			int s1 = findSetOptimized(i);
			int s2 = findSetOptimized(j);
			
			if(s1 != s2)
				unionSetOptimized(s1,s2);
			else 
			{
				System.out.println("Same Set, " + s1 + " and " + s2 );
				return true;
			}
			
		}
		return false;
	}
	public static void main(String[] args) {
		DSUGraph dg = new DSUGraph(4);
		dg.addEdge(0, 1);
		dg.addEdge(1, 2);
		dg.addEdge(2, 3);
		dg.addEdge(3, 0);
		
		System.out.println(dg.cycleCheck());
	}
}
