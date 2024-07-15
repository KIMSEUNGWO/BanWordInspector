package ban.inspector.config;

import ban.inspector.factory.BanWordFactory;
import ban.inspector.factory.ExceptWordFactory;
import ban.inspector.updater.WordChecker;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InnerInspectConfig {

    private final BanWordFactory banWordFactory;
    private final ExceptWordFactory exceptWordFactory;
    private InspectConfig inspectConfig;
    private final WordChecker wordChecker;

    @Autowired
    public InnerInspectConfig(BanWordFactory banWordFactory, ExceptWordFactory exceptWordFactory, WordChecker wordChecker) {
        this.banWordFactory = banWordFactory;
        this.exceptWordFactory = exceptWordFactory;
        this.wordChecker = wordChecker;
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

        banWordFactory.add(wordChecker.getDefaultBanWords());
        exceptWordFactory.add(wordChecker.getDefaultExceptWords());

        banWordFactory.build();
        exceptWordFactory.build();
    }

    public BanWordFactory getBanWordFactory() {
        return banWordFactory;
    }

    public ExceptWordFactory getExceptWordFactory() {
        return exceptWordFactory;
    }

}
