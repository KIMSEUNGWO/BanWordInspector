package ban.inspector.utils;

import ban.inspector.dto.WordDto;

import java.util.*;

public abstract class AbstractWordUtil extends WordData implements BanWordUtil, ExceptWordUtil {

    @Override
    public void addWord(String word) {
        push(word);
    }

    @Override
    public List<WordDto> has(String str) {
        return useToArray(str);
    }

    @Override
    public List<WordDto> filter(String newWord, List<WordDto> beforeWords) {
        System.out.println("AbstractWordUtil ===");
        System.out.println("newWord = " + newWord);
        System.out.println("beforeWords = " + beforeWords);
        if (beforeWords.isEmpty()) return List.of();
        List<WordDto> wordDtos = expectFilter(newWord, beforeWords);
        System.out.println("wordDtos = " + wordDtos);
        return wordDtos;
//        return (!beforeWords.isEmpty()) ? expectFilter(newWord, beforeWords) : List.of();
    }
}
