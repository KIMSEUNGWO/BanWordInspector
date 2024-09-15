package ban.inspector.factory;

import ban.inspector.utils.wordutils.BanWordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class BanWordFactoryImpl implements WordFactoryBuilder<BanWordUtil> {

    private final BanWordUtil banWordUtil;
    private final Set<String> builders = new HashSet<>();

    @Autowired
    public BanWordFactoryImpl(BanWordUtil banWordUtil) {
        this.banWordUtil = banWordUtil;
    }

    @Override
    public WordFactory add(List<String> words) {
        builders.addAll(words);
        return this;
    }

    @Override
    public BanWordUtil build() {
        builders.forEach(banWordUtil::addWord);
        builders.clear();
        banWordUtil.build();
        return banWordUtil;
    }

}
