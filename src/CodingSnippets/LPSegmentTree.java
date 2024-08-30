package CodingSnippets;

import java.util.Arrays;

public class LPSegmentTree {
	int tree[];
	int lazy[];
    int n;
    public LPSegmentTree(int n)
    {
        this.n = n;
        int size = (2 * (int)Math.pow(2, Math.ceil(Math.log(n) / Math.log(2))) - 1);
        this.tree = new int[size];
        this.lazy = new int[size];
    }

    public int queryComputation(int start, int end, int left, int right, int node)
    {
        if(start > right || end  < left) // no overlap
            return 0;
        
        int leftChild = (2*node) + 1;
        int rightChild = (2*node) + 2;
        if(lazy[node] != 0)
        {
        	tree[node] += lazy[node] * (end - start + 1);
        	if(start != end)
        	{
        		lazy[leftChild] += lazy[node];
        		lazy[rightChild] += lazy[node];
        	}
        	lazy[node] = 0;
        }
        if(start >= left && end <= right) // complete overlap
            return tree[node];
        
        //partial overlap
        int mid = start + (end - start)/2; 
        int q1 = queryComputation(start, mid, left, right, leftChild);
        int q2 = queryComputation(mid+1, end, left, right, rightChild);

        return q1 + q2;
    }
    public int query(int left, int right)
    {
        return queryComputation(0, n-1, left, right, 0);
    }
    
    public void buildFunction(int start, int end, int node, int arr[])
    {
    	if(start == end)
    	{
    		tree[node] = arr[start];
    		return;
    	}
    	
    	int mid = start + (end - start) / 2;
    	int leftChild = (2 * node) + 1;
        int rightChild = (2 * node) + 2;
    	buildFunction(start, mid, leftChild, arr);
    	buildFunction(mid + 1,end, rightChild , arr);

        tree[node] = tree[leftChild] + tree[rightChild];
    	
    }
    public void build(int arr[])
    {
    	buildFunction(0, n-1, 0, arr);
    }
    public void updateFunction(int start, int end,int node, int left, int right, int value)
    {
    	if(start > right || end  < left) // no overlap
            return;
        
        int leftChild = (2*node) + 1;
        int rightChild = (2*node) + 2;
        if(lazy[node] != 0)
        {
        	tree[node] += lazy[node] * (end - start + 1);
        	if(start != end)
        	{
        		lazy[leftChild] += lazy[node];
        		lazy[rightChild] += lazy[node];
        	}
        	lazy[node] = 0;
        }
        if(start >= left && end <= right) // complete overlap
        {
        	tree[node] += value * (end - start + 1);
        	if(start != end)
        	{
        		lazy[leftChild] += value;
        		lazy[rightChild] += value;
        	}
        	return;
        }
        
        //partial overlap
        int mid = start + (end - start)/2; 
        updateFunction(start, mid, leftChild, left, right, value);
        updateFunction(mid+1, end, rightChild, left, right, value);

        tree[node] = tree[leftChild] + tree[rightChild];
        return;
    }
    public void update(int left, int right, int value)
    {
    	updateFunction(0, n-1, 0, left, right, value);
    }
    public static void main(String[] args) 
    {
		int arr[] = {1,2,3,4,5,6,7,8};
		LPSegmentTree tree = new LPSegmentTree(8);
		tree.build(arr);

		System.out.println(Arrays.toString(tree.tree));
		tree.update(1,3,20);
		System.out.println(tree.query(0,2));
		System.out.println(Arrays.toString(tree.tree));
		
	}
}
