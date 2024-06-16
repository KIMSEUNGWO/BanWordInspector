package ban.inspector.utils;

import ban.inspector.dto.WordDto;

import java.util.List;

public interface WordUtil {

    void addWord(String word);
    List<WordDto> has(String word);

}
