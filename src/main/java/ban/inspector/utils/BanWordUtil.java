package ban.inspector.utils;

import ban.inspector.dto.WordDto;

import java.util.ArrayList;
import java.util.List;

public interface BanWordUtil extends WordUtil {

    default List<WordDto> has(String word) {
        return useToArray(word);
    }

    private List<WordDto> useToArray(String word) {
        List<WordDto> words = new ArrayList<>();

        for (int i = 0; i < word.length(); i++) {
            int txt = find(word, i);
            if (txt != -1) {
                words.add(new WordDto(word.substring(i, i + txt), i));
                i += txt - 1;
            }
        }

        return words;
    }

}
