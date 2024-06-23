package ban.inspector.customConfig.innerConfig;

import ban.inspector.customConfig.*;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InnerInspectConfig {

    @Getter private final BanWordFactory banWordFactory;
    @Getter private final ExceptWordFactory exceptWordFactory;
    private InspectConfig inspectConfig;
    private final WordUpdater wordUpdater;

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

}
