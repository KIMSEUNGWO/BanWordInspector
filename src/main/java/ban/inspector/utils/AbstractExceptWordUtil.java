package ban.inspector.utils;

import ban.inspector.dto.WordDto;

import java.util.List;

public abstract class AbstractExceptWordUtil extends WordData implements ExceptWordUtil {

    @Override
    public final void addWord(String word) {
        push(word);
    }

    @Override
    public final List<WordDto> filter(String newWord, List<WordDto> beforeWords) {
        return (beforeWords.isEmpty()) ? List.of() : expectFilter(newWord, beforeWords);
    }


}
