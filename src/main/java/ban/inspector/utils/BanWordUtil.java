package ban.inspector.utils;

import ban.inspector.dto.WordDto;

import java.util.List;

public abstract class BanWordUtil extends WordUtilImpl {

    public abstract List<WordDto> filter(String word);

}
