package ban.inspector.utils.wordutils;

import ban.inspector.dto.Word;

import java.util.List;

public interface ExceptWordUtil {

    List<Word> filter(String newWord, List<Word> beforeWords);
}
