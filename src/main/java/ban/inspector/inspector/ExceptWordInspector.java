package ban.inspector.inspector;

import ban.inspector.config.InspectConfig;
import ban.inspector.dto.WordDto;
import ban.inspector.utils.ExceptWordUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExceptWordInspector {

    private final InspectConfig config;
    private final ExceptWordUtil exceptWordUtil;

    public ExceptWordInspector(InspectConfig config, @Qualifier("exceptWordUtil") ExceptWordUtil exceptWordUtil) {
        this.config = config;
        this.exceptWordUtil = exceptWordUtil;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        config.initExceptWords().forEach(exceptWordUtil::addWord);
    }

    public List<String> inspect(String newWord, List<WordDto> badWords) {
        return exceptWordUtil.filter(newWord, badWords).stream()
            .map(WordDto::getWord)
            .toList();
    }

}
