package ban.inspector.inspector;

import ban.inspector.customConfig.innerConfig.InnerInspectConfig;
import ban.inspector.dto.Word;
import ban.inspector.utils.BanWordUtil;
import ban.inspector.utils.ExceptWordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Component
public class InspectorImpl implements Inspector {


    private final List<BanWordUtil> banWordUtils;
    private final List<ExceptWordUtil> exceptWordUtils;

    @Autowired
    public InspectorImpl(InnerInspectConfig config) {
        this.banWordUtils = config.getBanWordFactory().getUtils();
        this.exceptWordUtils = config.getExceptFactory().getUtils();
    }

    private List<Word> executeBanWord(String word) {
        Set<Word> set = new TreeSet<>(Word::compareTo);
        banWordUtils.forEach(util -> set.addAll(util.filter(word)));
        return new ArrayList<>(set);
    }

    private List<Word> executeExceptWord(String word, List<Word> beforeWords) {
        exceptWordUtils.forEach(util -> util.filter(word, beforeWords));
        return beforeWords;
    }


    @Override
    public List<Word> inspect(String word) {
        return executeExceptWord(word, executeBanWord(word));
    }
}
