package ban.inspector.utils;

import ban.inspector.dto.WordDto;

import java.util.List;

public abstract class AbstractBanWordUtil extends WordData implements BanWordUtil {

    @Override
    public final List<WordDto> has(String word) {
        return useToArray(word);
    }

    @Override
    public final void addWord(String word) {
        push(word);
    }
}
