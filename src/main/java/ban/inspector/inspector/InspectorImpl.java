package ban.inspector.inspector;

import ban.inspector.config.InnerInspectConfig;
import ban.inspector.dto.Word;
import ban.inspector.utils.wordutils.BanWordUtil;
import ban.inspector.utils.wordutils.ExceptWordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InspectorImpl implements Inspector {

    private final BanWordUtil banWordUtil;
    private final ExceptWordUtil exceptWordUtil;

    @Autowired
    public InspectorImpl(InnerInspectConfig config) {
        System.out.println("InspectorImpl.InspectorImpl");
        banWordUtil = config.getBanWordUtil();
        exceptWordUtil = config.getExceptWordUtil();
    }

    private List<Word> executeBanWord(String word) {
        return banWordUtil.filter(word);
    }

    private List<Word> executeExceptWord(String word, List<Word> beforeWords) {
        return exceptWordUtil.filter(word, beforeWords);
    }

    @Override
    public List<Word> inspect(String word) {
        return executeExceptWord(word, executeBanWord(word));
    }
}
