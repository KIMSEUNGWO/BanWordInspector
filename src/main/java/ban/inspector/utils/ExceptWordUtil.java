package ban.inspector.utils;

import ban.inspector.dto.WordDto;

import java.util.List;

public interface ExceptWordUtil extends WordUtil {

    List<WordDto> filter(String newWord, List<WordDto> badWords);

}
