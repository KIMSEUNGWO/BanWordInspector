package ban.inspector.inspector;

import ban.inspector.config.InspectConfig;
import ban.inspector.dto.WordDto;
import ban.inspector.utils.WordUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BanWordInspector {

    private final InspectConfig config;
    private final WordUtil banWordUtil;

    public BanWordInspector(InspectConfig config, @Qualifier("banWordUtil") WordUtil banWordUtil) {
        this.config = config;
        this.banWordUtil = banWordUtil;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        config.initBanWords().forEach(banWordUtil::addWord);
    }

    public List<WordDto> inspect(String newWord) {
        return banWordUtil.has(newWord);
    }
}
