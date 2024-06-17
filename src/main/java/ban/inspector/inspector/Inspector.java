package ban.inspector.inspector;

import ban.inspector.dto.Word;

import java.util.List;

public interface Inspector {

    List<Word> inspect(String word);
}
