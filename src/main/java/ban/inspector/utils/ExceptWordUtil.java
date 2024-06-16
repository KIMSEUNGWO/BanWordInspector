package ban.inspector.utils;

import ban.inspector.dto.WordDto;

import java.util.List;

public interface ExceptWordUtil extends WordUtil {

    default List<WordDto> filter(String newWord, List<WordDto> beforeWords) {
        return (beforeWords.isEmpty()) ? List.of() : expectFilter(newWord, beforeWords);
    }

    private List<WordDto> expectFilter(String newWord, List<WordDto> beforeWords) {
        StringBuilder sb = new StringBuilder(newWord);
        for (int i = 0; i < beforeWords.get(beforeWords.size()-1).getEndIndex(); i++) {
            String txt = find(sb.toString(), i);
            if (txt != null) {
                int endIndex = i + txt.length();
                sb.replace(i, endIndex, "x".repeat(txt.length()));
                i += txt.length() - 1;
            }
        }
        return beforeWords.stream()
                .filter(wordDto -> wordDto.isSame(sb))
                .toList();
    }

}
