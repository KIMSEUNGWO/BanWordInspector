package ban.inspector.utils;

import ban.inspector.dto.WordDto;

import java.util.List;

public abstract class ExceptWordUtil extends WordUtilImpl {

    public final List<WordDto> filter(String newWord, List<WordDto> beforeWords) {
        return (beforeWords.isEmpty()) ? List.of() : expectFilter(newWord, beforeWords);
    }

    abstract public List<WordDto> expectFilter(String newWord, List<WordDto> beforeWords);

}
