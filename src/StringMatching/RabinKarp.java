package StringMatching;

import java.util.*;

public class RabinKarp {
    
    // Function to search pattern occurrences in text
    public static List<Integer> search(String text, String pattern, int base, int mod) {
        List<Integer> result = new ArrayList<>();

        int n = text.length();
        int m = pattern.length();

        if (m > n) return result;

        // Precompute power = base^(m-1) % mod
        long power = 1;
        for (int i = 0; i < m - 1; i++) 
            power = (power * base) % mod;

        long patternHash = 0;
        long textHash = 0;

        // Calculate initial hash for pattern and first window of text
        for (int i = 0; i < m; i++) 
        {
            patternHash = (patternHash * base + pattern.charAt(i)) % mod;
            textHash = (textHash * base + text.charAt(i)) % mod;
        }

        // Slide the pattern over text
        for (int i = 0; i <= n - m; i++) 
        {
            // If hash matches, check characters one by one
            if (patternHash == textHash) 
            {
                if (text.substring(i, i + m).equals(pattern))
                    result.add(i); // match found
            }

            // Rolling hash: remove left char, shift, and add right char
            if (i < n - m) 
            {
                textHash = (textHash - text.charAt(i) * power % mod + mod) % mod; // remove leftmost char
                textHash = (textHash * base + text.charAt(i + m)) % mod;          // shift and add right char
            }
        }

        return result;
    }

    public static void main(String[] args) {
        String text = "abcdabcabc";
        String pattern = "abc";

        int base = 256;      // Common base (can be 128, 256, or size of charset)
        int mod = 1000000007; // Large prime to avoid overflow

        List<Integer> matches = search(text, pattern, base, mod);

        System.out.println("Pattern found at indices: " + matches);
    }
}
