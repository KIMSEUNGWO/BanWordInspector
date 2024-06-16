package ban.inspector.utils;

import ban.inspector.dto.WordDto;

import java.util.List;

public interface BanWordUtil extends WordUtil {

    List<WordDto> has(String word);

}
