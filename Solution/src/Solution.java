import java.util.*;

class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    boolean isEnd = false;
}

public class Solution{

    TrieNode root = new TrieNode();
    HashMap<String, Integer> frequency = new HashMap<>();

    // Insert query into trie
    public void insert(String query) {

        TrieNode node = root;

        for (char c : query.toCharArray()) {
            node.children.putIfAbsent(c, new TrieNode());
            node = node.children.get(c);
        }

        node.isEnd = true;

        frequency.put(query, frequency.getOrDefault(query, 0) + 1);
    }

    // DFS to collect suggestions
    void collect(TrieNode node, String prefix, List<String> results) {

        if (node.isEnd)
            results.add(prefix);

        for (char c : node.children.keySet()) {
            collect(node.children.get(c), prefix + c, results);
        }
    }

    // Search prefix suggestions
    public List<String> search(String prefix) {

        TrieNode node = root;

        for (char c : prefix.toCharArray()) {
            if (!node.children.containsKey(c))
                return new ArrayList<>();
            node = node.children.get(c);
        }

        List<String> results = new ArrayList<>();
        collect(node, prefix, results);

        // Sort by frequency
        results.sort((a, b) -> frequency.get(b) - frequency.get(a));

        return results.subList(0, Math.min(10, results.size()));
    }

    public static void main(String[] args) {

        AutocompleteSystem system = new AutocompleteSystem();

        system.insert("java tutorial");
        system.insert("javascript");
        system.insert("java tutorial");
        system.insert("java download");
        system.insert("java download");
        system.insert("java download");

        List<String> suggestions = system.search("jav");

        int rank = 1;

        for (String s : suggestions) {
            System.out.println(rank + ". " + s + " (" + system.frequency.get(s) + " searches)");
            rank++;
        }
    }
}