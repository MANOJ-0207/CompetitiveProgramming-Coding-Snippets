package StringMatching;

// Separate Computation needed for different patterns
public class ZAlgorithm {

    // Function to build Z-array
    public static int[] computeZArray(String s) {
        int n = s.length();
        int[] z = new int[n];

        int left = 0, right = 0;
        for (int i = 1; i < n; i++) 
        {
            if (i <= right)
                z[i] = Math.min(right - i + 1, z[i - left]);
            
            while (i + z[i] < n && s.charAt(z[i]) == s.charAt(i + z[i])) 
                z[i]++;
         
            if (i + z[i] - 1 > right) 
            {
                left = i;
                right = i + z[i] - 1;
            }
        }
        return z;
    }

    // Function to search pattern in text using Z-algorithm
    public static void search(String text, String pattern) 
    {
        String concat = pattern + "$" + text;
        int[] z = computeZArray(concat);

        int patternLength = pattern.length();

        for (int i = 0; i < z.length; i++) 
        {
            if (z[i] == patternLength) 
                System.out.println("Pattern found at index " + (i - patternLength - 1));
        }
    }

    // Test
    public static void main(String[] args) {
        String text = "abxabcabcaby";
        String pattern = "abcaby";

        search(text, pattern);
    }
}
