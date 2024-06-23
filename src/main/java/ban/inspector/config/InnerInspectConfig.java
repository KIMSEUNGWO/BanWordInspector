package ban.inspector.config;

import ban.inspector.factory.BanWordFactory;
import ban.inspector.factory.ExceptWordFactory;
import ban.inspector.updater.WordUpdater;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InnerInspectConfig {

    private final BanWordFactory banWordFactory;
    private final ExceptWordFactory exceptWordFactory;
    private InspectConfig inspectConfig;
    private final WordUpdater wordUpdater;

    @Autowired
    public InnerInspectConfig(BanWordFactory banWordFactory, ExceptWordFactory exceptWordFactory, WordUpdater wordUpdater) {
        this.banWordFactory = banWordFactory;
        this.exceptWordFactory = exceptWordFactory;
        this.wordUpdater = wordUpdater;
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

        wordUpdater.update();
        banWordFactory.add(wordUpdater.getDefaultBanWords());
        exceptWordFactory.add(wordUpdater.getDefaultExceptWords());

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
