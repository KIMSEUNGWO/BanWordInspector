package ban.inspector.utils;


import java.util.HashMap;
import java.util.Map;

public abstract class AbstractWordUtil implements WordUtil {

    private final Map<Character, WordUtil> data = new HashMap<>();

    @Override
    public void push(String word, int index) {
        if (word.length() <= index) return;

        char c = word.charAt(index);

        if (!data.containsKey(c)) data.put(c, getInstance());
        data.get(c).push(word, index + 1);
    }


    @Override
    public int find(String str, int idx) {
        if (data.isEmpty()) return 0;
        if (str.length() <= idx || !data.containsKey(str.charAt(idx))) return -1;
        int next = data.get(str.charAt(idx)).find(str, idx + 1);
        return (next == -1) ? -1 : 1 + next;
    }

}
