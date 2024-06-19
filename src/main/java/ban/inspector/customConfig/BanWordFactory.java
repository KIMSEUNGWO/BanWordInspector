package ban.inspector.customConfig;

import ban.inspector.dto.Word;

import java.util.List;

public interface BanWordFactory {

    List<Word> filter(String word);
}
