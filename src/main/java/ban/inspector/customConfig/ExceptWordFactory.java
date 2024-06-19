package ban.inspector.customConfig;

import ban.inspector.dto.Word;

import java.util.List;

public interface ExceptWordFactory extends WordFactory {

    List<Word> filter(String word, List<Word> banWords);
}
