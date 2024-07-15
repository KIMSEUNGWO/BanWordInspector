package ban.inspector.utils.wordutils;

import ban.inspector.dto.Word;
import ban.inspector.utils.AhoCorasickWordUtil;
import ban.inspector.utils.WordUtil2;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BanWordUtil {

    private final WordUtil2 wordUtil2 = new AhoCorasickWordUtil();

    public final List<Word> filter(String word) {
        return wordUtil2.search(word);
    }

    public void addWord(String word) {
        wordUtil2.addWord(word);
    }


    public void build() {
        wordUtil2.build();
    }
}
