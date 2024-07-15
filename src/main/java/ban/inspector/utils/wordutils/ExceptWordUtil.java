package ban.inspector.utils.wordutils;

import ban.inspector.dto.Word;
import ban.inspector.utils.AhoCorasickWordUtil;
import ban.inspector.utils.WordUtil2;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ExceptWordUtil {

    private final WordUtil2 wordUtil2 = new AhoCorasickWordUtil();

    public final List<Word> filter(String newWord, List<Word> beforeWords) {
        return (beforeWords.isEmpty()) ? List.of() : expectFilter(newWord, beforeWords);
    }

    private List<Word> expectFilter(String newWord, List<Word> beforeWords) {
        List<Word> exceptWords = wordUtil2.search(newWord);

        if (exceptWords.isEmpty()) return beforeWords;

        List<Word> newWords = new ArrayList<>();

        a:for (Word banWord : beforeWords) {
            for (Word exceptWord : exceptWords) {
                if (banWord.isInclude(exceptWord)) continue a;
            }
            newWords.add(banWord);
        }
        return newWords;
    }

    public void addWord(String word) {
        wordUtil2.addWord(word);
    }

    public void build() {
        wordUtil2.build();
    }
}
