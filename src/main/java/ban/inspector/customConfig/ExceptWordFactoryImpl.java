package ban.inspector.customConfig;

import ban.inspector.dto.Word;
import ban.inspector.utils.ExceptWordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ExceptWordFactoryImpl implements WordFactory, ExceptWordFactory {

    private final ExceptWordUtil exceptWordUtil;
    private final List<String> builders = new ArrayList<>();

    @Override
    public WordFactory add(List<String> words) {
        builders.addAll(words);
        builders.forEach(exceptWordUtil::addWord);
        return this;
    }

    @Override
    public WordFactory build() {
        builders.forEach(exceptWordUtil::addWord);
        return this;
    }

    @Override
    public List<Word> filter(String word, List<Word> banWords) {
        return exceptWordUtil.filter(word, banWords);
    }
}
