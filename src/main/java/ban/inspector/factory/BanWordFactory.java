package ban.inspector.factory;

import ban.inspector.dto.Word;
import ban.inspector.utils.wordutils.BanWordUtil;

import java.util.List;

public interface BanWordFactory extends WordFactory{

    List<Word> filter(String word);
    BanWordUtil build();

}
