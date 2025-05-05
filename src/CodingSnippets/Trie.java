package CodingSnippets;

import java.util.*;
class TrieNode 
{
    Map<Character, TrieNode> children = new HashMap<>();
    boolean isEndOfWord = false;
}

public class Trie 
{
    private final TrieNode root;

    public Trie() 
    {
        root = new TrieNode();
    }
    public void insert(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            current = current.children.computeIfAbsent(ch, c -> new TrieNode());
        }
        current.isEndOfWord = true;
    }

    public String findShortestRoot(String word) {
        TrieNode current = root;
        StringBuilder prefix = new StringBuilder();
        for (char ch : word.toCharArray()) {
            if (current.isEndOfWord) {
                return prefix.toString();
            }
            if (!current.children.containsKey(ch)) {
                return null;
                
            }
            prefix.append(ch);
            current = current.children.get(ch);
        }
        return current.isEndOfWord ? prefix.toString() : null;
    }
    
    public boolean search(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            TrieNode node = current.children.get(ch);
            if (node == null) {
                return false;
            }
            current = node;
        }
        return current.isEndOfWord;
    }
    
    public boolean startsWith(String prefix) {
        TrieNode current = root;
        for (char ch : prefix.toCharArray()) {
            TrieNode node = current.children.get(ch);
            if (node == null) {
                return false;
            }
            current = node;
        }
        return true;
    }
    
    public static void main(String[] args) 
    {
		Trie trie = new Trie();
		
		trie.insert("apple");
		System.out.println(trie.search("apple"));   // returns true
		System.out.println(trie.search("app"));     // returns false
		System.out.println(trie.startsWith("app")); // returns true
		
		trie.insert("app");
		System.out.println(trie.search("app"));     // returns true
    }
}