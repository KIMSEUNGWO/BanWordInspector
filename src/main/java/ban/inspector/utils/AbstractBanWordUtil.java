package ban.inspector.utils;

import ban.inspector.dto.WordDto;

import java.util.List;

public abstract class AbstractBanWordUtil implements BanWordUtil {

    private final WordData data = new WordData();

    @Override
    public final List<WordDto> has(String word) {
        return data.useToArray(word);
    }

    @Override
    public final void addWord(String word) {
        data.push(word);
    }
}
