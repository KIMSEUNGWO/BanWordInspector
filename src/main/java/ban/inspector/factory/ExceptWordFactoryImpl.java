package ban.inspector.factory;

import ban.inspector.dto.Word;
import ban.inspector.utils.wordutils.ExceptWordUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ExceptWordFactoryImpl implements ExceptWordFactory {

    private final ExceptWordUtil exceptWordUtil;
    private final List<String> builders = new ArrayList<>();

    public ExceptWordFactoryImpl(ExceptWordUtil exceptWordUtil) {
        this.exceptWordUtil = exceptWordUtil;
    }

    @Override
    public WordFactory add(List<String> words) {
        builders.addAll(words);
        return this;
    }

    @Override
    public void build() {
        builders.forEach(exceptWordUtil::addWord);
        exceptWordUtil.build();
    }

    @Override
    public List<Word> filter(String word, List<Word> banWords) {
        return exceptWordUtil.filter(word, banWords);
    }
}
