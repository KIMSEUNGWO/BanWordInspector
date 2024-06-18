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
    public ExceptWordFactoryBuilder add(ExceptWordUtil exceptWordUtil) {
        return new ExceptWordFactoryBuilder(this, exceptWordUtil);
    }

    public List<ExceptWordUtil> getUtils() {
        return builders;
    }

    private void build(ExceptWordUtil exceptWordUtil) {
        builders.add(exceptWordUtil);
    }

    @Override
    public Iterator<ExceptWordUtil> iterator() {
        return builders.iterator();
    }

    public static class ExceptWordFactoryBuilder {

        private final ExceptWordFactoryImpl factory;
        private final ExceptWordUtil wordUtil;

        public ExceptWordFactoryBuilder(ExceptWordFactoryImpl factory, ExceptWordUtil wordUtil) {
            this.factory = factory;
            this.wordUtil = wordUtil;
        }

        public ExceptWordFactoryBuilder initWords(InitWords words) {
            words.initWords().forEach(wordUtil::addWord);
            return this;
        }

        public void build() {
            factory.build(wordUtil);
        }
    }
}
