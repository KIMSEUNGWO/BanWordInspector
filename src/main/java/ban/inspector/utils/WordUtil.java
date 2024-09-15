package ban.inspector.utils;

import ban.inspector.dto.Word;

import java.util.List;

public interface WordUtil {

    void addWord(String word);
    void build();
    List<Word> search(String word);
}
