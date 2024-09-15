package ban.inspector.utils;

import ban.inspector.dto.Word;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class AhoCorasickWordUtil implements WordUtil2 {

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

    @Override
    public void addWord(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            node = node.children.computeIfAbsent(c, k -> new TrieNode());
        }
        node.output.add(word);
    }

    @Override
    public void build() {
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

    @Override
    public List<Word> search(String word) {
        List<Word> result = new ArrayList<>();
        TrieNode node = root;

        int realIndex = 0;       // 실제 문자열에서의 인덱스를 추적하는 변수
        int nonSpaceIndex = 0;   // 공백을 제외한 문자 인덱스

        // 공백을 포함한 문자의 시작과 끝 인덱스를 저장할 맵
        Map<Integer, Integer> startIndices = new HashMap<>();

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            // 공백은 무시하고 실제 인덱스만 증가
            if (Character.isWhitespace(c)) {
                realIndex++;
                continue;
            }

            // 공백을 제외한 인덱스를 기록
            startIndices.put(nonSpaceIndex, realIndex);

            // Trie를 탐색하면서 실패 링크를 통해 처리
            while (node != root && !node.children.containsKey(c)) {
                node = node.failureLink;
            }

            if (node.children.containsKey(c)) {
                node = node.children.get(c);
            }

            // 출력 리스트에 포함된 패턴들을 결과 리스트에 추가
            for (String pattern : node.output) {
                int patternLength = pattern.length();
                int start = startIndices.getOrDefault(nonSpaceIndex - (patternLength - 1), -1);
                int end = realIndex + 1;

                if (start != -1) {
                    result.add(new Word(pattern, start, end));
                }
            }

            nonSpaceIndex++;
            realIndex++;
        }

        System.out.println("result = " + result);
        return result;
    }





}
