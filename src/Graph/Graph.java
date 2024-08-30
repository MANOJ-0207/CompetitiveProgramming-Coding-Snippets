package Graph;
import java.util.*;
class Graph
{
    int n;
    ArrayList<Integer> edgeList [];
    ArrayList<Integer> topologicalOrder;
    boolean visited[];
    Graph(int count)
    {
        this.n = count+1;
        this.topologicalOrder = new ArrayList<Integer>();
        this.edgeList = new ArrayList[n];
        this.visited = new boolean[n];
        ArrayList<Integer> topologicalOrder;
        for (int i = 0; i < n; i++) 
            edgeList[i] = new ArrayList<>();
        
    }

    
    void topoDFS(int curr, int par)
    {
    	visited[curr] = true;
    	for(int neighbour : edgeList[curr])
    	{
    		if(!visited[neighbour])
    			topoDFS(neighbour,curr);
    	}
    	topologicalOrder.add(curr);
    	return;
    }

    
    void addEdge(int v1, int v2, boolean directed)
    {
        edgeList[v1].add(v2);
        if(!directed)
            edgeList[v2].add(v1);
    }
    void bfs(int source, boolean[] visited)
    {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(source);
        visited[source] = true;

        while(!queue.isEmpty())
        {
            int v = queue.poll();
            System.out.println(v);
            for(int neighbour : edgeList[v])
            {
                if(!visited[neighbour])
                {
                    queue.add(neighbour);
                    visited[neighbour] = true;
                }
            }
        }
    }
    
    void bfsTraversal() {
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                bfs(i, visited);
            }
        }
    }
    
    void shortestDistanceBFS(int source, int dest)
    {
    	boolean visited[] = new boolean[n];
    	int distance[] = new int[n];
    	for(int i=0;i<n;i++)
    		distance[i] = -1;
    	int parent[] = new int[n];
    	
    	for(int i=0; i<n; i++)
    		parent[i] = -1;
    	
    	
    	Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(source);
        visited[source] = true;
        parent[source] = source;
        distance[source] = 0;

        while(!queue.isEmpty())
        {
            int v = queue.poll();
            System.out.println(v);
            for(int neighbour : edgeList[v])
            {
                if(!visited[neighbour])
                {
                    queue.add(neighbour);
                    visited[neighbour] = true;
                    parent[neighbour] = v;
                    distance[neighbour] = distance[v] + 1;
                }
            }
        }
        
        for(int i=0; i<n; i++)
        	System.out.println("Shortest Distance from " + source + " to " + i + " is " + distance[i]);
        
        if(dest != -1)
        {
        	int temp = dest;
        	while(temp != source)
        	{
        		System.out.print(temp + "--");
        		temp = parent[temp];
        	}
        	System.out.print(source);
        }
    }
    void dfsHelper(int node, boolean visited[])
    {
    	visited[node] = true;
    	System.out.println(node);
    	for(int neighbour : edgeList[node])
    	{
    		if(!visited[neighbour])
    			dfsHelper(neighbour,visited);
    	}
    }
    void dfs(int source, boolean visited[])
    {
    	dfsHelper(source,visited);
    }
    
    void dfsTraversal()
    {
    	boolean[] visited = new boolean[n];
    	for (int i = 0; i < n; i++) {
            if (!visited[i]) 
                dfs(i, visited);
        }
    }
    
    void dfsBackEdgeDetector(int node, int parent, boolean visited[])
    {
    	visited[node] = true;
    	for(int neighbour : edgeList[node])
    	{
    		if(!visited[neighbour])
    			dfsBackEdgeDetector(neighbour,node,visited);
    		else 
    		{
    			if(neighbour != parent)
    				System.out.println(neighbour + " --- " + node);
    		}
    	}
    }
    
//    8
//    9
//    0 3
//    0 4
//    1 3
//    2 4
//    2 7
//    3 5
//    3 6
//    3 7
//    4 6
    
    
//    8
//    8
//    0 1
//    0 2
//    1 4
//    2 3
//    3 5
//    4 5
//    5 6
//    6 7
    
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	int n = sc.nextInt();
    	int edgecount = sc.nextInt();
    	Graph g = new Graph(n);
    	
    	for(int i=0;i < edgecount;i++)
    	{
    		int v1 = sc.nextInt();
    		int v2 = sc.nextInt();
    		g.addEdge(v1,v2,false);
    	}
    	boolean visited[] = new boolean[n];
    	g.bfs(0,visited);
    	System.out.println("=========================================");
    	g.bfsTraversal();
    	System.out.println("=========================================");
    	visited = new boolean[n];
    	g.dfs(0,visited);
    	System.out.println("=========================================");
    	g.dfsTraversal();
    	System.out.println("=========================================");
    	g.shortestDistanceBFS(4, 5);
    	g.topoDFS(0, -1);
//    	Collections.reverse(g.topologicalOrder);
//    	System.out.println(g.topologicalOrder);
    	sc.close();
    }
		
}