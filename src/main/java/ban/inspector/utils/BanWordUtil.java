package ban.inspector.utils;

import ban.inspector.dto.WordDto;

import java.util.ArrayList;
import java.util.List;

public abstract class BanWordUtil extends WordUtilImpl {

    public final List<WordDto> filter(String word) {
        String newWord = setWordForm(word);
        return useToArray(newWord);
    }

    protected abstract String setWordForm(String word);

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
