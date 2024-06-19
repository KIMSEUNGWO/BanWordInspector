package ban.inspector.customConfig;

import ban.inspector.dto.Word;

import java.util.List;

public interface ExceptWordFactory {

    List<Word> filter(String word, List<Word> banWords);
}
