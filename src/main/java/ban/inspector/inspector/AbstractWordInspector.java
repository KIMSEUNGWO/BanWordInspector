package ban.inspector.inspector;

import ban.inspector.config.InspectConfig;
import ban.inspector.utils.WordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.List;

@RequiredArgsConstructor
public abstract class AbstractWordInspector {

    private final InspectConfig config;

    @EventListener(ApplicationReadyEvent.class)
    private void onApplicationReady() {
        initWords(config).forEach(wordUtil()::addWord);
    }

    protected abstract WordUtil wordUtil();
    protected abstract List<String> initWords(InspectConfig config);
}
