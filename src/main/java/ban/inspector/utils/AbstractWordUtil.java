package ban.inspector.utils;

import ban.inspector.dto.WordDto;

import java.util.*;

public abstract class AbstractWordUtil implements WordUtil {

    private final Map<Character, AbstractWordUtil> data = new HashMap<>();

    @Override
    public void addWord(String word) {
        push(word, 0);
    }

    private void push(String word, int idx) {
        if (word.length() <= idx) return;

        char c = word.charAt(idx);

        if (!data.containsKey(c)) data.put(c, getInstance());
        data.get(c).push(word, idx + 1);

    }

    protected abstract AbstractWordUtil getInstance();


    @Override
    public List<WordDto> has(String str) {
        return useToArray(str);
    }

    private List<WordDto> useToArray(String str) {
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

}
