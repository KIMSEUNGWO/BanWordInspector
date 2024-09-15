package ban.inspector.config;

import ban.inspector.factory.WordFactoryBuilder;
import ban.inspector.updater.WordLoader;
import ban.inspector.utils.wordutils.BanWordUtilImpl;
import ban.inspector.utils.wordutils.ExceptWordUtilImpl;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InnerInspectConfig {

    private final WordFactoryBuilder<BanWordUtilImpl> banWordFactory;
    private final WordFactoryBuilder<ExceptWordUtilImpl> exceptWordFactory;
    private InspectConfig inspectConfig;
    private final WordLoader wordLoader;

    @Autowired
    public InnerInspectConfig(WordFactoryBuilder<BanWordUtilImpl> banWordFactory, WordFactoryBuilder<ExceptWordUtilImpl> exceptWordFactory, WordLoader wordLoader) {
        this.banWordFactory = banWordFactory;
        this.exceptWordFactory = exceptWordFactory;
        this.wordLoader = wordLoader;
    }

    @Autowired(required = false)
    public void setInspectConfig(InspectConfig inspectConfig) {
        this.inspectConfig = inspectConfig;
    }

    @PostConstruct
    private void onApplicationReady() {
        if (inspectConfig != null) {
            inspectConfig.addBanWords(banWordFactory);
            inspectConfig.addExceptWords(exceptWordFactory);
        }

        banWordFactory.add(wordLoader.readBanWords());
        exceptWordFactory.add(wordLoader.readExceptWords());
    }

    public BanWordUtilImpl getBanWordUtil() {
        return banWordFactory.build();
    }

    public ExceptWordUtilImpl getExceptWordUtil() {
        return exceptWordFactory.build();
    }

}
