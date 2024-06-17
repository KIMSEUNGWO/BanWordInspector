package ban.inspector.customConfig.innerConfig;

import ban.inspector.customConfig.BanWordFactory;
import ban.inspector.customConfig.ExceptWordFactory;
import ban.inspector.customConfig.InspectConfig;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InnerInspectConfig {

    @Getter private final BanWordFactory banWordFactory;
    @Getter private final ExceptWordFactory exceptFactory;
    private final InspectConfig inspectConfig;

    @EventListener(ApplicationReadyEvent.class)
    private void onApplicationReady() {
        inspectConfig.addBanWordInspector(banWordFactory);
        inspectConfig.addExceptWordInspector(exceptFactory);
    }

}
