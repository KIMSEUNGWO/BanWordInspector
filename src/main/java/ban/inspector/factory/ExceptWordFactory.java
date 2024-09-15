package ban.inspector.factory;

import ban.inspector.dto.Word;
import ban.inspector.utils.wordutils.ExceptWordUtil;

import java.util.List;

public interface ExceptWordFactory extends WordFactory {

    List<Word> filter(String word, List<Word> banWords);
    ExceptWordUtil build();

}
