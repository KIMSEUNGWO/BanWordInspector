package ban.inspector.utils;


import ban.inspector.dto.WordDto;

import java.util.ArrayList;
import java.util.List;

public class BanWordUtilImpl extends BanWordUtil {


    @Override
    public List<WordDto> filter(String word) {
        String newWord = word.replaceAll("[^가-힣]", "");
        return useToArray(newWord);
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
