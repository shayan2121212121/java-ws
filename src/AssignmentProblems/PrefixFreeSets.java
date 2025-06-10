package AssignmentProblems;

import java.util.*;

public class PrefixFreeSets {

    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isWord = false;
    }

    public int maxElements(String[] words) {
        // Sort the words so that shorter prefixes are inserted first
        Set<String> wordSet = new HashSet<>(Arrays.asList(words));
        String[] uniqueWords = wordSet.toArray(new String[0]);
        Arrays.sort(uniqueWords);
        TrieNode root = new TrieNode();
        for (String word : uniqueWords) {
            insert(root, word);
        }
        return countLeaves(root);
    }

    private void insert(TrieNode root, String word) {
        TrieNode curr = root;
        for (char ch : word.toCharArray()) {
            curr = curr.children.computeIfAbsent(ch, c -> new TrieNode());
        }
        curr.isWord = true;
    }

    private int countLeaves(TrieNode node) {
        if (node.children.isEmpty()) {
            return 1; // Leaf node corresponds to a prefix-free word
        }
        int count = 0;
        for (TrieNode child : node.children.values()) {
            count += countLeaves(child);
        }
        return count;
    }

    // Test cases
    public static void main(String[] args) {
        PrefixFreeSets solver = new PrefixFreeSets();

        System.out.println(solver.maxElements(new String[]{"hello", "hi", "h", "run", "rerun", "running"})); // 4
        System.out.println(solver.maxElements(new String[]{"a", "b", "cba", "cbc", "cbb", "ccc"})); // 6
        System.out.println(solver.maxElements(new String[]{"a", "ab", "abc", "abcd", "abcde", "abcdef"})); // 1
        System.out.println(solver.maxElements(new String[]{"topcoder", "topcoder", "topcoding"})); // 2
    
}
}






