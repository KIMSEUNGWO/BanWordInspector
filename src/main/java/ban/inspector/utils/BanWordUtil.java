package ban.inspector.utils;

import ban.inspector.dto.WordDto;

import java.util.ArrayList;
import java.util.List;

public interface BanWordUtil extends WordUtil {

    default List<WordDto> has(String word) {
        return useToArray(word);
    }

    private List<WordDto> useToArray(String str) {
        List<WordDto> words = new ArrayList<>();

        for (int i = 0; i < str.length(); i++) {
            String txt = find(str, i);
            if (txt != null) {
                words.add(new WordDto(txt, i));
                i += txt.length() - 1;
            }
        }

        return words;
    }

}
