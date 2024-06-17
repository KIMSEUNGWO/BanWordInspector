package ban.inspector.utils;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class WordUtilImpl implements WordUtil {

    private final Map<Character, WordUtilImpl> data = new HashMap<>();

    @Override
    public void push(String word, int index) {
        if (word.length() <= index) return;

        char c = word.charAt(index);

        if (!data.containsKey(c)) data.put(c, new WordUtilImpl());
        data.get(c).push(word, index + 1);
    }


    @Override
    public int find(String str, int index, int deep, boolean ignoreSpace) {
        WordUtilImpl wordUtil = this;
        while (true) {
            if (wordUtil.data.isEmpty()) return deep;
            if (str.length() <= index) return -1;

            if (ignoreSpace && str.charAt(index) == ' ') {
                if (deep == 0) return -1;
                index++;    deep++;
                continue;
            }

            if (!wordUtil.data.containsKey(str.charAt(index))) return -1;
            wordUtil = wordUtil.data.get(str.charAt(index));
            index++;    deep++;
        }
    }

}
