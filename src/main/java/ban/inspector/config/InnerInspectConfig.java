package ban.inspector.config;

import ban.inspector.factory.WordFactoryBuilder;
import ban.inspector.updater.WordLoader;
import ban.inspector.updater.WordUpdater;
import ban.inspector.utils.wordutils.BanWordUtil;
import ban.inspector.utils.wordutils.ExceptWordUtil;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InnerInspectConfig {

    private final WordFactoryBuilder<BanWordUtil> banWordFactory;
    private final WordFactoryBuilder<ExceptWordUtil> exceptWordFactory;
    private final WordLoader wordLoader;
    private final WordUpdater wordUpdater;
    private InspectConfig inspectConfig;

    @Autowired
    public InnerInspectConfig(WordFactoryBuilder<BanWordUtil> banWordFactory, WordFactoryBuilder<ExceptWordUtil> exceptWordFactory, WordLoader wordLoader, WordUpdater wordUpdater) {
        this.banWordFactory = banWordFactory;
        this.exceptWordFactory = exceptWordFactory;
        this.wordLoader = wordLoader;
        this.wordUpdater = wordUpdater;
    }

    @Autowired(required = false)
    public void setInspectConfig(InspectConfig inspectConfig) {
        this.inspectConfig = inspectConfig;
    }

    @PostConstruct
    private void onApplicationReady() {
        wordUpdater.update();
        if (inspectConfig != null) {
            inspectConfig.addBanWords(banWordFactory);
            inspectConfig.addExceptWords(exceptWordFactory);
        }

        banWordFactory.add(wordLoader.readBanWords());
        exceptWordFactory.add(wordLoader.readExceptWords());
    }

    public BanWordUtil getBanWordUtil() {
        return banWordFactory.build();
    }

    public ExceptWordUtil getExceptWordUtil() {
        return exceptWordFactory.build();
    }

}
