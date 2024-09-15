package ban.inspector.factory;

import ban.inspector.utils.wordutils.ExceptWordUtil;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class ExceptWordFactoryImpl implements WordFactoryBuilder<ExceptWordUtil> {

    private final ExceptWordUtil exceptWordUtil;
    private final Set<String> builders = new HashSet<>();

    public ExceptWordFactoryImpl(ExceptWordUtil exceptWordUtil) {
        this.exceptWordUtil = exceptWordUtil;
    }

    @Override
    public WordFactory add(List<String> words) {
        builders.addAll(words);
        return this;
    }

    @Override
    public ExceptWordUtil build() {
        builders.forEach(exceptWordUtil::addWord);
        builders.clear();
        exceptWordUtil.build();
        return exceptWordUtil;
    }

}
