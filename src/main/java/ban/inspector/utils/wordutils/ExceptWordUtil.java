package ban.inspector.utils.wordutils;

import ban.inspector.dto.Word;
import ban.inspector.utils.WordUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ExceptWordUtil extends AbstractWordUtil<ExceptWordUtil> {

    public ExceptWordUtil(@Qualifier("except") WordUtil wordUtil) {
        super(wordUtil);
    }

    public final List<Word> filter(String newWord, List<Word> beforeWords) {
        return (beforeWords.isEmpty()) ? List.of() : expectFilter(newWord, beforeWords);
    }

    private List<Word> expectFilter(String newWord, List<Word> beforeWords) {
        List<Word> exceptWords = wordUtil.search(newWord);

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

    @Override
    public ExceptWordUtil get() {
        return this;
    }
}
