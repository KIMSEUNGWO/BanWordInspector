package ban.inspector.utils;

import ban.inspector.dto.WordDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExceptWordUtil extends AbstractWordUtil {

    @Override
    protected AbstractWordUtil getInstance() {
        return new ExceptWordUtil();
    }

    public List<WordDto> filter(String newWord, List<WordDto> badWords) {
        if (badWords.isEmpty()) return List.of();

        StringBuilder sb = new StringBuilder(newWord);
        for (int i = 0; i < badWords.get(badWords.size()-1).getEndIndex(); i++) {
            String txt = find(sb.toString(), i);
            if (txt != null) {
                int endIndex = i + txt.length();
                sb.replace(i, endIndex, "x".repeat(endIndex));
                i += txt.length() - 1;
            }
        }
        return badWords.stream()
            .filter(x -> x.getWord().equals(sb.substring(x.getStartIndex(), x.getEndIndex())))
            .toList();
    }
}