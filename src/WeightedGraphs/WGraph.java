package WeightedGraphs;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;

import DisjointSetUnion.DSUGraph;

public class WGraph 
{
	int n;
	List<Edge> edgeList[];
	int adjacencyMatrix[][];
	List<DetailedEdge>detailedEdgeList;
	WGraph(int count)
	{
		n = count + 1;
		adjacencyMatrix = new int[n][n];
		for(int i=0; i<n; i++)
		{
			int row[] = new int[n];
			Arrays.fill(row,20000);
			adjacencyMatrix[i] = row;
			adjacencyMatrix[i][i] = 0;
			detailedEdgeList = new ArrayList<DetailedEdge>();
		}
		edgeList = new ArrayList[n];
		for(int i=0; i < n; i++)
			edgeList[i] = new ArrayList<Edge>();
		
	}
	
	void addEdge(int v1, int v2, int weight, boolean undirected)
	{
		edgeList[v1].add(new Edge(v2,weight)); 
		if(undirected)
			edgeList[v2].add(new Edge(v1,weight));
		adjacencyMatrix[v1][v2] = weight;
		if(undirected)
			adjacencyMatrix[v2][v1] = weight;
		detailedEdgeList.add(new DetailedEdge(v1,v2,weight));
		
	}
	public int[][] floydWarshall()
	{
		int dist[][] = new int[n][n];
		for(int i=0; i<n; i++)
		{
			for(int j=0; j<n; j++)
				dist[i][j] = adjacencyMatrix[i][j];
		}
		
		for(int k=0; k<n; k++)
		{
			for(int i=0; i<n; i++)
			{
				for(int j=0; j<n; j++)
				{
					if(dist[i][j] > dist[i][k] + dist[k][j])
						dist[i][j] = dist[i][k] + dist[k][j];
				}
			}
		}
		for(int i=0; i<n; i++)
		{
			for(int j=0; j<n; j++)
			{
				if(dist[i][j] > dist[i][1] + dist[1][j]) 
					return null; // negative weight cycle
			}
		}
		return dist;
	}
	
	// works for negative weights
	void bellmanford(int v, int source)
	{
		int dist[] = new int[v+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[source] = 0;
		
		for(int i=0; i<(v-1); i++)
		{
			for(int current = 0; current <= v; current++)
			{
				ArrayList<Edge> edges = (ArrayList<Edge>) edgeList[current];
				for(Edge e : edges)
				{
					int neighbour = e.dest;
					int currentEdge = e.distance;
					
					if(dist[current] != Integer.MAX_VALUE && dist[current] + currentEdge < dist[neighbour])
						dist[neighbour] = dist[current] + currentEdge;
				}
			}
		}
		for(int current = 0; current <= v; current++)
		{
			ArrayList<Edge> edges = (ArrayList<Edge>) edgeList[current];
			for(Edge e : edges)
			{
				int neighbour = e.dest;
				int currentEdge = e.distance;
				
				if(dist[current] != Integer.MAX_VALUE && dist[current] + currentEdge < dist[neighbour]) {
					System.out.println("Negative Weight Cycle Detected!!");
					return;
				}
			}
		}
		for(int i=0; i <= v; i++)	
			System.out.println(i + " is at " + dist[i]);
	}
	
	// for Positive Weights only
	int dijikstra(int source, int destination)
	{
		int dist[] = new int[n];
		TreeSet<Edge> set = new TreeSet<Edge>();
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[source] = 0;
		set.add(new Edge(source,0));
		while(!set.isEmpty())
		{
			Edge firstEdge = set.first();
			int node = firstEdge.dest;
			int distanceTillNow = firstEdge.distance;
			set.remove(firstEdge);
//			for(Edge ed : set)
//				System.out.println(ed.dest + " " + ed.distance);
			for(Edge e : edgeList[node])
			{
//				System.out.println(node + "-> " + e.dest);
				int neighbour = e.dest;
				int currentEdge = e.distance;
				if(distanceTillNow + currentEdge < dist[neighbour])
				{
					Edge old = new Edge(neighbour, dist[neighbour]);
					set.remove(old);
					dist[neighbour] = distanceTillNow + currentEdge;
					set.add(new Edge(neighbour,dist[neighbour]));
				}
			}

		}
		return dist[destination];
	}
	
	public  void primsMST(int source)
	{
		PriorityQueue<Edge> edgeQueue = new PriorityQueue<Edge>();
		boolean visited[] = new boolean[n];
		int cost = 0;
		
		edgeQueue.add(new Edge(source,0));
		ArrayList<Edge> mstEdges = new ArrayList<Edge>();
		while(!edgeQueue.isEmpty())
		{
			Edge first = edgeQueue.poll();	
			int to = first.dest;
			int weight = first.distance;
			if(visited[to])
				continue;
			mstEdges.add(first);
			cost += weight;
			visited[to] = true;
			
			for(Edge e : edgeList[to])
			{
				if(!visited[e.dest])
					edgeQueue.add(e);	
			}
				
		}
		System.out.println(cost);
		for(Edge e : mstEdges)
			System.out.println(e);
	}
	
	public int kruskalMST()
	{
		DSUGraph dsu = new DSUGraph(n);
		Collections.sort(detailedEdgeList);
		int cost = 0;
		for(DetailedEdge de : detailedEdgeList)
		{
			int v1 = de.source;
			int v2 = de.dest;
			int w = de.distance;
			
			if(dsu.findSetOptimized(v1) != dsu.findSetOptimized(v2))
			{
				dsu.unionSetOptimized(v1,v2);
				cost += w;
			}
		}
		return cost;
	}
	
	public static void main(String[] args) {
		WGraph g = new WGraph(9);
//		g.addEdge(0,1,1,true);
//		g.addEdge(1,2,1,true);
//		g.addEdge(0,2,4,true);
//		g.addEdge(0,3,7,true);
//		g.addEdge(3,2,2,true);
//		g.addEdge(3,4,3,true);
//		System.out.println(g.dijikstra(0, 3));
//		System.out.println(g.dijikstra(1, 3));
//		System.out.println(g.dijikstra(0, 4));
//		System.out.println(g.dijikstra(3, 0));
		
		
//		g.addEdge(1,2,3,false);
//		g.addEdge(2,3,4,false);
//		g.addEdge(1,3,-10,false);
//		g.bellmanford(3, 1);
		
		
//		g.addEdge(0, 2, -2, false);
//		g.addEdge(1, 0, 4, false);
//		g.addEdge(1, 2, 3, false);
//		g.addEdge(2, 3, 2, false);
//		g.addEdge(3, 1, -1, false);
//		int dist[][] = g.floydWarshall();
//		if(dist == null)
//			System.out.println("Negative Weight Cycle");
//		else
//			for(int row[] : dist)
//				System.out.println(Arrays.toString(row));
		
		g.addEdge(1,2,10,true);
		g.addEdge(1,3,12,true);
		g.addEdge(2,4,8,true);
		g.addEdge(2,3,9,true);
		g.addEdge(3,5,3,true);
		g.addEdge(3,6,1,true);
		g.addEdge(4,5,7,true);
		g.addEdge(4,6,10,true);
		g.addEdge(4,7,8,true);
		g.addEdge(4,8,5,true);
		g.addEdge(5,6,3,true);
		g.addEdge(6,8,6,true);
		g.addEdge(7,8,9,true);
		g.addEdge(7,9,2,true);
		g.addEdge(8,9,11,true);
		g.primsMST(1);
		System.out.print(g.kruskalMST());
		
	}
	
}
