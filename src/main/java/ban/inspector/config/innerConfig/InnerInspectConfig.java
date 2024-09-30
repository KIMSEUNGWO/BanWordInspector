package ban.inspector.config.innerConfig;

import ban.inspector.config.InspectConfig;
import ban.inspector.factory.WordFactoryBuilder;
import ban.inspector.utils.wordutils.BanWordUtil;
import ban.inspector.utils.wordutils.ExceptWordUtil;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InnerInspectConfig {

    private final WordFactoryBuilder<BanWordUtil> banWordFactory;
    private final WordFactoryBuilder<ExceptWordUtil> exceptWordFactory;
    private InspectConfig inspectConfig;

    @Autowired
    public InnerInspectConfig(WordFactoryBuilder<BanWordUtil> banWordFactory, WordFactoryBuilder<ExceptWordUtil> exceptWordFactory) {
        this.banWordFactory = banWordFactory;
        this.exceptWordFactory = exceptWordFactory;
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
    }

    public BanWordUtil getBanWordUtil() {
        return banWordFactory.build();
    }

    public ExceptWordUtil getExceptWordUtil() {
        return exceptWordFactory.build();
    }

}
