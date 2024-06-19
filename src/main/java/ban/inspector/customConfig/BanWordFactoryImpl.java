package ban.inspector.customConfig;

import ban.inspector.dto.Word;
import ban.inspector.utils.BanWordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class BanWordFactoryImpl implements BanWordFactory {

    private final BanWordUtil banWordUtil;
    private final Set<String> builders = new TreeSet<>();

    @Override
    public WordFactory add(List<String> words) {
        builders.addAll(words);
        return this;
    }

    @Override
    public void build() {
        builders.forEach(banWordUtil::addWord);
    }

    @Override
    public List<Word> filter(String word) {
        return banWordUtil.filter(word);
    }
}
