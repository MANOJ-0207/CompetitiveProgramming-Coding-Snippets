package CodingSnippets;

import java.util.ArrayList;
import java.util.Arrays;

public class PrimeSeive 
{
	public static boolean[] primeSeive(int n)
	{
		boolean primeSeive[] = new boolean[n+1];
		Arrays.fill(primeSeive, true);
		
		for(int i = 2; i<=n; i++)
		{
			if(primeSeive[i]) // prime condition
			{
				for(int j= i*i; j<=n; j+=i)
					primeSeive[j] = true;
			}
		}
		return primeSeive;
	}
	public static int[] primeSeiveForPrimeFactorization(int n)
	{
		int primeSeive[] = new int[n+1];
		for(int i=1; i<=n; i++)
			primeSeive[i] = i;
		
		for(int i = 2; i<=n; i++)
		{
			if(primeSeive[i] == i) // prime condition
			{
				for(int j= i*i; j<=n; j+=i)
				{
					if(primeSeive[j] == j)
						primeSeive[j] = i; // prime factor
				}
			}
		}
		return primeSeive;
	}
	
	
	public static ArrayList<Integer> getFactors(int x, int primeSeive[])
	{
		ArrayList<Integer> factors = new ArrayList<Integer>();
		while(x != 1)
		{
			factors.add(primeSeive[x]); 
			x /= primeSeive[x];
		}
		return factors;
	}
	
	// if n > 10 ^ 7 (40 MB), limit for array size
	// can cover upto 10 ^ 9
	// segment tree is shifted by n positions
	// mapped primeseive for element in range m to n
	public static boolean[] segmentedPrimeSeive(int m, int n)
	{
		ArrayList<Integer> primes = new ArrayList<Integer>();
		int sq = (int) Math.sqrt(n);
		boolean prime[] = new boolean[sq+1];
		Arrays.fill(prime, true);
		for(int i=2; i <= sq; i++)
		{
			if(prime[i])
			{
				primes.add(i);
				for(int j = i*i; j <= sq; j+=i)
					prime[i] = false;	
			}
		}
		boolean segmentSeive[] = new boolean[n-m+1]; 
		Arrays.fill(segmentSeive, true);
		for(int p : primes)
		{
			if(p * p > n)
				break;
			
			int start = (m/p) * p;
			if(p >= m && p <= n)
				start = 2 * p;
			
			for(int j = start; j<=n; j+=p)
			{
				if(j < m)
					continue;
				
				segmentSeive[j-m] = false; // shift by m positions
			}
		}
		
		return segmentSeive;
//		for(int i=m; i<=n; i++)
//		{
//			if(segmentSeive[i-m] == false && i != 1)
//				System.out.println(i + " ");
//		}
		
	}
	
	
}
