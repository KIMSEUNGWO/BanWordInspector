package ban.inspector.inspector;

import ban.inspector.customConfig.BanWordFactory;
import ban.inspector.customConfig.ExceptWordFactory;
import ban.inspector.customConfig.innerConfig.InnerInspectConfig;
import ban.inspector.dto.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Component
public class InspectorImpl implements Inspector {


    private final BanWordFactory banWordFactory;
    private final ExceptWordFactory exceptWordFactory;

    @Autowired
    public InspectorImpl(InnerInspectConfig config) {
        banWordFactory = config.getBanWordFactory();
        exceptWordFactory = config.getExceptFactory();
    }

    private List<Word> executeBanWord(String word) {
        Set<Word> set = new TreeSet<>(Word::compareTo);
        banWordFactory.forEach(util -> set.addAll(util.filter(word)));
        return new ArrayList<>(set);
    }

    private List<Word> executeExceptWord(String word, List<Word> beforeWords) {
        exceptWordFactory.forEach(util -> util.filter(word, beforeWords));
        return beforeWords;
    }


    @Override
    public List<Word> inspect(String word) {
        return executeExceptWord(word, executeBanWord(word));
    }
}
