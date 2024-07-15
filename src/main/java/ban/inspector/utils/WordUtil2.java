package ban.inspector.utils;

import ban.inspector.dto.Word;

import java.util.List;
import java.util.Map;

public interface WordUtil2 {

    void addWord(String word);
    void build();
    List<Word> search(String word);
}
