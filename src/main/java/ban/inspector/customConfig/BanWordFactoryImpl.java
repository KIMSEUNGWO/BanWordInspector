package ban.inspector.customConfig;

import ban.inspector.utils.BanWordUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BanWordFactoryImpl implements BanWordFactory {

    private List<BanWordUtil> builders = new ArrayList<>();

    @Override
    public BanWordFactoryBuilder add(BanWordUtil banWordUtil) {
        return new BanWordFactoryBuilder(this, banWordUtil);
    }

    public List<BanWordUtil> getUtils() {
        return builders;
    }

    private void build(BanWordUtil banWordUtil) {
        builders.add(banWordUtil);
    }

    public static class BanWordFactoryBuilder {

        private final BanWordFactoryImpl factory;
        private final BanWordUtil wordUtil;


        public BanWordFactoryBuilder(BanWordFactoryImpl factory, BanWordUtil wordUtil) {
            this.factory = factory;
            this.wordUtil = wordUtil;
        }

        public BanWordFactoryBuilder initWords(InitWords words) {
            words.initWords().forEach(wordUtil::addWord);
            return this;
        }

        public void build() {
            factory.build(wordUtil);
        }
    }
}
