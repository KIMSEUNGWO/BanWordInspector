package ban.inspector.utils.wordutils;

import ban.inspector.dto.Word;
import ban.inspector.utils.AhoCorasickWordUtil;
import ban.inspector.utils.WordUtil;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BanWordUtil {

    private final WordUtil wordUtil = new AhoCorasickWordUtil();

    public final List<Word> filter(String word) {
        return wordUtil.search(word);
    }

    public void addWord(String word) {
        wordUtil.addWord(word);
    }


    public void build() {
        wordUtil.build();
    }
}
