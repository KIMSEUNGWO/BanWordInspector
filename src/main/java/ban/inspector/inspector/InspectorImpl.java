package ban.inspector.inspector;

import ban.inspector.customConfig.BanWordFactory;
import ban.inspector.customConfig.BanWordFactoryImpl;
import ban.inspector.customConfig.ExceptWordFactory;
import ban.inspector.customConfig.ExceptWordFactoryImpl;
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
        exceptWordFactory = config.getExceptWordFactory();
    }

    private List<Word> executeBanWord(String word) {
        return banWordFactory.filter(word);
    }

    private List<Word> executeExceptWord(String word, List<Word> beforeWords) {
        return exceptWordFactory.filter(word, beforeWords);
    }

    @Override
    public List<Word> inspect(String word) {
        return executeExceptWord(word, executeBanWord(word));
    }
}
