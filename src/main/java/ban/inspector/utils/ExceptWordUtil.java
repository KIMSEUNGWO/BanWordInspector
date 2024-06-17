package ban.inspector.utils;

import ban.inspector.dto.WordDto;

import java.util.List;

public abstract class ExceptWordUtil extends WordUtilImpl {

    abstract public List<WordDto> filter(String newWord, List<WordDto> beforeWords);

}
