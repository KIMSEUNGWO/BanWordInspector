package ban.inspector.factory;

import ban.inspector.utils.wordutils.ExceptWordUtilImpl;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class ExceptWordFactoryImpl implements WordFactoryBuilder<ExceptWordUtilImpl> {

    private final ExceptWordUtilImpl exceptWordUtil;
    private final Set<String> builders = new HashSet<>();

    public ExceptWordFactoryImpl(ExceptWordUtilImpl exceptWordUtil) {
        this.exceptWordUtil = exceptWordUtil;
    }

    @Override
    public WordFactory add(List<String> words) {
        builders.addAll(words);
        return this;
    }

    @Override
    public ExceptWordUtilImpl build() {
        builders.forEach(exceptWordUtil::addWord);
        exceptWordUtil.build();
        return exceptWordUtil;
    }

}
