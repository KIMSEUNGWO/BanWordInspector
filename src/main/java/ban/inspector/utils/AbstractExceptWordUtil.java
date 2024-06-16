package ban.inspector.utils;

import ban.inspector.dto.WordDto;

import java.util.List;

public abstract class AbstractExceptWordUtil implements ExceptWordUtil {

    private final WordData data = new WordData();
    
    @Override
    public final void addWord(String word) {
        data.push(word);
    }

    @Override
    public final List<WordDto> filter(String newWord, List<WordDto> beforeWords) {
        return (beforeWords.isEmpty()) ? List.of() : data.expectFilter(newWord, beforeWords);
    }


}
