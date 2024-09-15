package ban.inspector.factory;

import ban.inspector.utils.wordutils.BanWordUtilImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class BanWordFactoryImpl implements WordFactoryBuilder<BanWordUtilImpl> {

    private final BanWordUtilImpl banWordUtil;
    private final Set<String> builders = new HashSet<>();

    @Autowired
    public BanWordFactoryImpl(BanWordUtilImpl banWordUtil) {
        this.banWordUtil = banWordUtil;
    }

    @Override
    public WordFactory add(List<String> words) {
        builders.addAll(words);
        return this;
    }

    @Override
    public BanWordUtilImpl build() {
        builders.forEach(banWordUtil::addWord);
        banWordUtil.build();
        return banWordUtil;
    }

}
