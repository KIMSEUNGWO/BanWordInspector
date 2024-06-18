package ban.inspector.customConfig;

import ban.inspector.utils.ExceptWordUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class ExceptWordFactoryImpl implements ExceptWordFactory {

    private final List<ExceptWordUtil> builders = new ArrayList<>();

    @Override
    public WordFactoryBuilder add(ExceptWordUtil exceptWordUtil) {
        return new ExceptWordFactoryBuilder(this, exceptWordUtil);
    }

    @Override
    public Iterator<ExceptWordUtil> iterator() {
        return builders.iterator();
    }

    public static class ExceptWordFactoryBuilder implements WordFactoryBuilder {

        private final ExceptWordFactoryImpl factory;
        private final ExceptWordUtil wordUtil;

        public ExceptWordFactoryBuilder(ExceptWordFactoryImpl factory, ExceptWordUtil wordUtil) {
            this.factory = factory;
            this.wordUtil = wordUtil;
        }

        @Override
        public WordFactoryBuilder initWords(InitWords words) {
            words.initWords().forEach(wordUtil::addWord);
            return this;
        }

        @Override
        public void build() {
            factory.builders.add(wordUtil);
        }
    }
}
