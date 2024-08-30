package GraphTree;

import java.util.*;
class TreeGraph
{
    int n;
    ArrayList<Integer> edgeList [];
    int parent[];
    int depth[];
    boolean visited[];
    ArrayList<Integer> topologicalOrder;
    TreeGraph(int n)
    {
        this.n = n+1;
        this.edgeList = new ArrayList[n];
        this.parent = new int[n];
        this.depth = new int[n];
        this.visited = new boolean[n];
        this.topologicalOrder = new ArrayList<Integer>();
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
    int LCA(int v1, int v2)
    {
    	if(v1 == v2)
    		return v1;
    	if(depth[v1] < depth[v2])
    	{
    		int temp = v1;
	    	v1 = v2;
	    	v2 = temp;
    	}
    	int diff = depth[v1] - depth[v2];
    	while(diff-- > 0) 
    		v1 = parent[v1];
    	
    	while(v1 != v2)
    	{
    		v1 = parent[v1];
    		v2 = parent[v2];
    	}
    	return v1;
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
    	for(int i=0;i < n;i++)
    		distance[i] = -1;
    	int parent[] = new int[n];
    	
    	for(int i=0; i < n; i++)
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
        
        for(int i=0; i < n; i++)
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
    
    void dfsHelper(int node, int par)
    {
    	System.out.println(node);
    	parent[node] = par;
    	depth[node] = depth[par] + 1;
    	for(int neighbour : edgeList[node])
    	{
    		if(neighbour != par)
    			dfsHelper(neighbour,node);
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
    
    
    
    
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	int n = sc.nextInt();
    	int edgecount = sc.nextInt();
    	TreeGraph tg = new TreeGraph(n);
    	
    	for(int i=0;i < edgecount;i++)
    	{
    		int v1 = sc.nextInt();
    		int v2 = sc.nextInt();
    		tg.addEdge(v1,v2,false);
    	}
//    	boolean visited[] = new boolean[n];
//    	g.bfs(0,visited);
//    	System.out.println("=========================================");
//    	g.bfsTraversal();
//    	System.out.println("=========================================");
//    	visited = new boolean[n];
//    	g.dfs(0,visited);
//    	System.out.println("=========================================");
//    	g.dfsTraversal();
//    	System.out.println("=========================================");
//    	g.shortestDistanceBFS(0, 6);
    	tg.dfsHelper(1,0);
    	
    	System.out.println("=========================================");
    	System.out.println(Arrays.toString(tg.depth));
    	System.out.println(Arrays.toString(tg.parent));
    	
    	System.out.println(tg.LCA(9,6));
    	System.out.println(tg.LCA(7,6));
    	System.out.println(tg.LCA(2,6));
    	System.out.println(tg.LCA(9,10));
    	sc.close();
    }
		
}
