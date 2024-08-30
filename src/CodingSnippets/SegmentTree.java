package CodingSnippets;
import java.util.Arrays;

public class SegmentTree
{
    int tree[];
    int n;
    public SegmentTree(int n)
    {
        this.n = n;
        int size = (2 * (int)Math.pow(2, Math.ceil(Math.log(n) / Math.log(2))) - 1);
        this.tree = new int[size];
    }

    public int queryComputation(int start, int end, int left, int right, int node)
    {
        if(start > right || end  < left) // no overlap
            return 0;
        
        if(start >= left && end <= right) // complete overlap
            return tree[node];
        
        //partial overlap
        int mid = start + (end - start)/2; 
        int q1 = queryComputation(start, mid, left, right, (2*node) + 1);
        int q2 = queryComputation(mid+1, end, left, right, (2*node) + 2);

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
    public void updateFunction(int start, int end,int node, int index, int value)
    {
    	if(start == end)
    	{
    		tree[node] = value;
    		return;
    	}
    	
    	int mid = start + (end- start)/2;
    	int leftChild = (2 * node) + 1;
    	int rightChild = (2 * node) + 2;
    	if(index <= mid)
    		updateFunction(start, mid, leftChild, index, value);
    	else
    		updateFunction(mid+1, end, rightChild, index, value);
    	
    	tree[node] = tree[leftChild] + tree[rightChild];
    }
    public void update(int index, int value)
    {
    	updateFunction(0, n-1,0, index, value);
    }
    public static void main(String[] args) 
    {
		int arr[] = {1,2,3,4,5,6,7,8,9,10};
		SegmentTree tree = new SegmentTree(10);
		tree.build(arr);

		System.out.println(Arrays.toString(tree.tree));
		tree.update(4, 20);
		System.out.println(tree.query(4,6));
		System.out.println(Arrays.toString(tree.tree));
		
	}
}
