package ban.inspector.utils;

import java.util.*;

public class AhoCorasickWordUtil {

    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        TrieNode failureLink = null;
        Set<String> output = new HashSet<>();

        public TrieNode() {}
    }

    private final TrieNode root;

    public AhoCorasickWordUtil() {
        this.root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            node = node.children.computeIfAbsent(c, k -> new TrieNode());
        }
        node.output.add(word);
    }

    public void buildFailureLinks() {
        Queue<TrieNode> queue = new LinkedList<>();
        root.failureLink = root;

        for (TrieNode node : root.children.values()) {
            node.failureLink = root;
            queue.add(node);
        }

        while (!queue.isEmpty()) {
            TrieNode current = queue.poll();

            for (Map.Entry<Character, TrieNode> entry : current.children.entrySet()) {
                char c = entry.getKey();
                TrieNode child = entry.getValue();

                TrieNode failure = current.failureLink;
                while (failure != root && !failure.children.containsKey(c)) {
                    failure = failure.failureLink;
                }

                if (failure.children.containsKey(c) && failure.children.get(c) != child) {
                    child.failureLink = failure.children.get(c);
                } else {
                    child.failureLink = root;
                }

                child.output.addAll(child.failureLink.output);
                queue.add(child);
            }
        }
    }

    public Map<String, List<Integer>> search(String word) {
        Map<String, List<Integer>> results = new HashMap<>();
        TrieNode node = root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            while (node != root && !node.children.containsKey(c)) {
                node = node.failureLink;
            }

            if (node.children.containsKey(c)) {
                node = node.children.get(c);
            }

            for (String pattern : node.output) {
                results.computeIfAbsent(pattern, k -> new ArrayList<>()).add(i - pattern.length() + 1);
            }
        }

        return results;
    }


}
