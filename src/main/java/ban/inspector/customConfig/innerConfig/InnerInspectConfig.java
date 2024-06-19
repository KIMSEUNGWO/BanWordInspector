package ban.inspector.customConfig.innerConfig;

import ban.inspector.customConfig.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InnerInspectConfig {

    @Getter private final BanWordFactoryImpl banWordFactory;
    @Getter private final ExceptWordFactoryImpl exceptWordFactory;
    private final InspectConfig inspectConfig;

    @EventListener(ApplicationReadyEvent.class)
    private void onApplicationReady() {
        inspectConfig.addBanWords(banWordFactory);
        inspectConfig.addExceptWords(exceptWordFactory);

        banWordFactory.build();
        exceptWordFactory.build();
    }

}
