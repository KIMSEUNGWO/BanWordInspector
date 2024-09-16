package ban.inspector.utils.wordutils;

import ban.inspector.dto.Word;
import ban.inspector.utils.WordUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ExceptWordUtil extends AbstractWordUtil {

    public ExceptWordUtil(@Qualifier("except") WordUtil wordUtil) {
        super(wordUtil);
    }

    public final List<Word> filter(String newWord, List<Word> banWords) {
        return (banWords.isEmpty()) ? List.of() : expectFilter(newWord, banWords);
    }

    private List<Word> expectFilter(String newWord, List<Word> banWords) {
        List<Word> exceptWords = wordUtil.search(newWord);

        if (exceptWords.isEmpty()) return banWords;

        List<Word> newWords = new ArrayList<>();

        a:for (Word banWord : banWords) {
            for (Word exceptWord : exceptWords) {
                if (banWord.isInclude(exceptWord)) continue a;
            }
            newWords.add(banWord);
        }
        return newWords;
    }

}
