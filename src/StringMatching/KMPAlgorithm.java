package StringMatching;

import java.util.ArrayList;
import java.util.List;

// One table computation requried for all patterns
public class KMPAlgorithm {

    // Method 1: Build the LPS (Longest Prefix Suffix) table for the pattern
    public static int[] buildLPS(String pattern) 
    {
        int m = pattern.length();
        int[] lps = new int[m];
        int len = 0;  // length of the previous longest prefix suffix

        // lps[0] is always 0, so we start from index 1
        int i = 1;
        while (i < m) 
        {
            if (pattern.charAt(i) == pattern.charAt(len)) 
            {
                len++;
                lps[i] = len;
                i++;
            } 
            else 
            {
                if (len != 0) 
                    // This is the tricky part: we do not increment i here
                    len = lps[len - 1];
                else 
                {
                    lps[i] = 0;
                    i++;
                }
            }
        }

        return lps;
    }
    // Method 2: KMP search
    // Returns list of starting indices where pattern occurs in text
    public static List<Integer> KMPSearch(String text, String pattern) {
        List<Integer> result = new ArrayList<>();
        int n = text.length();
        int m = pattern.length();

        int[] lps = buildLPS(pattern);

        int i = 0; // index for text
        int j = 0; // index for pattern

        while (i < n) 
        {
            if (text.charAt(i) == pattern.charAt(j)) 
            {
                i++;
                j++;
            }

            if (j == m) 
            {
                // Pattern found at index (i - j)
                result.add(i - j);
                j = lps[j - 1]; 
            }// Continue to check for next matches
            else if (i < n && text.charAt(i) != pattern.charAt(j)) 
            {
                if (j != 0) 
                    j = lps[j - 1];  // Jump to last known matching prefix
                else
                    i++;
            }
        }

        return result;
    }

    // Test main method
    public static void main(String[] args) {
        String text = "ababcababcababc";
        String pattern = "ababc";

        List<Integer> matches = KMPSearch(text, pattern);
        System.out.println("Pattern found at indices: " + matches);
    }
}

