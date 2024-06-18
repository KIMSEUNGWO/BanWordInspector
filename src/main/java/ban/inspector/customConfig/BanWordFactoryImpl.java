package ban.inspector.customConfig;

import ban.inspector.utils.BanWordUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class BanWordFactoryImpl implements BanWordFactory {

    private final List<BanWordUtil> builders = new ArrayList<>();

    @Override
    public WordFactoryBuilder add(BanWordUtil banWordUtil) {
        return new BanWordFactoryBuilder(this, banWordUtil);
    }

    @Override
    public Iterator<BanWordUtil> iterator() {
        return builders.iterator();
    }

    public static class BanWordFactoryBuilder implements WordFactoryBuilder {

        private final BanWordFactoryImpl factory;
        private final BanWordUtil wordUtil;


        public BanWordFactoryBuilder(BanWordFactoryImpl factory, BanWordUtil wordUtil) {
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
