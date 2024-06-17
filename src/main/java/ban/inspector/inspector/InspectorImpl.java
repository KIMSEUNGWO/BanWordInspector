package ban.inspector.inspector;

import ban.inspector.customConfig.innerConfig.InnerInspectConfig;
import ban.inspector.dto.WordDto;
import ban.inspector.utils.BanWordUtil;
import ban.inspector.utils.ExceptWordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    private List<WordDto> executeBanWord(String word) {
        if (banWordUtils.isEmpty()) return List.of();
        Set<WordDto> set = new TreeSet<>(WordDto::compareTo);

        for (BanWordUtil banWordUtil : banWordUtils) {
            List<WordDto> filter = banWordUtil.filter(word);
            set.addAll(filter);
        }
        return set.stream().toList();
    }

    private List<WordDto> executeExceptWord(String word, List<WordDto> beforeWords) {
        if (exceptWordUtils.isEmpty()) return List.of();

        for (ExceptWordUtil exceptWordUtil : exceptWordUtils) {
            exceptWordUtil.filter(word, beforeWords);
        }
        return beforeWords;
    }


    @Override
    public List<String> inspect(String word) {
        List<WordDto> banWords = executeBanWord(word);
        List<WordDto> exceptWords = executeExceptWord(word, banWords);
        return exceptWords.stream().map(WordDto::getWord).toList();
    }
}
