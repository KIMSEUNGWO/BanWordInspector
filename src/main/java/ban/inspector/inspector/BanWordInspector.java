package ban.inspector.inspector;

import ban.inspector.config.InspectConfig;
import ban.inspector.dto.WordDto;
import ban.inspector.utils.BanWordUtil;
import ban.inspector.utils.WordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BanWordInspector extends AbstractWordInspector {

    private final BanWordUtil wordUtil;

    @Autowired
    public BanWordInspector(InspectConfig config, BanWordUtil wordUtil) {
        super(config);
        this.wordUtil = wordUtil;
    }

    public List<WordDto> inspect(String newWord) {
        return wordUtil.has(newWord);
    }


    @Override
    protected WordUtil wordUtil() {
        return wordUtil;
    }

    @Override
    protected List<String> initWords(InspectConfig config) {
        return config.initBanWords();
    }
}
