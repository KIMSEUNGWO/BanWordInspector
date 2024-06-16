package ban.inspector.utils;

import ban.inspector.dto.WordDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordData {

    private final Map<Character, WordData> data = new HashMap<>();

    protected void push(String word) {
        push(word, 0);
    }

    private void push(String word, int idx) {
        if (word.length() <= idx) return;

        char c = word.charAt(idx);

        if (!data.containsKey(c)) data.put(c, new WordData());
        data.get(c).push(word, idx + 1);

    }

    protected List<WordDto> useToArray(String str) {
        List<WordDto> words = new ArrayList<>();

        for (int i = 0; i < str.length(); i++) {
            String txt = find(str, i);
            if (txt != null) {
                words.add(new WordDto(txt, i));
                i += txt.length() - 1;
            }
        }

        return words;
    }

    protected String find(String str, int idx) {
        if (data.isEmpty()) return "";
        if (str.length() <= idx || !data.containsKey(str.charAt(idx))) return null;
        String next = data.get(str.charAt(idx)).find(str, idx + 1);
        return (next != null) ? str.charAt(idx) + next : null;
    }


    protected List<WordDto> expectFilter(String newWord, List<WordDto> beforeWords) {
        StringBuilder sb = new StringBuilder(newWord);
        for (int i = 0; i < beforeWords.get(beforeWords.size()-1).getEndIndex(); i++) {
            String txt = find(sb.toString(), i);
            if (txt != null) {
                int endIndex = i + txt.length();
                sb.replace(i, endIndex, "x".repeat(txt.length()));
                i += txt.length() - 1;
            }
        }
        return beforeWords.stream()
            .filter(wordDto -> wordDto.isSame(sb))
            .toList();
    }

}
