package ban.inspector.utils.wordutils;

import ban.inspector.dto.Word;
import ban.inspector.utils.AbstractWordUtil;
import ban.inspector.utils.WordUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BanWordUtil extends AbstractWordUtil<BanWordUtil> {

    public BanWordUtil(@Qualifier("ban") WordUtil wordUtil) {
        super(wordUtil);
    }

    public final List<Word> filter(String word) {
        return wordUtil.search(word);
    }

    @Override
    public BanWordUtil get() {
        return this;
    }
}
