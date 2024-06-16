package ban.inspector.inspector;

import ban.inspector.config.InspectConfig;
import ban.inspector.dto.WordDto;
import ban.inspector.utils.ExceptWordUtil;
import ban.inspector.utils.WordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExceptWordInspector extends AbstractWordInspector {

    private final ExceptWordUtil wordUtil;

    @Autowired
    public ExceptWordInspector(InspectConfig config, @Qualifier("exceptWordUtil") ExceptWordUtil wordUtil) {
        super(config);
        this.wordUtil = wordUtil;
    }

    public List<String> inspect(String newWord, List<WordDto> badWords) {
        return wordUtil.filter(newWord, badWords).stream()
            .map(WordDto::getWord)
            .toList();
    }

    @Override
    protected WordUtil wordUtil() {
        return wordUtil;
    }

    @Override
    protected List<String> initWords(InspectConfig config) {
        return config.initExceptWords();
    }
}
