package ban.inspector.inspector;

import ban.inspector.dto.Word;

import java.util.List;

public interface Inspector {

    List<Word> inspect(String word);

    default String mask(String word) {
        return mask(word, "?");
    }

    default String mask(String word, String replace) {
        StringBuilder sb = new StringBuilder(word);
        int removeCharCount = 0;
        for (Word data : inspect(word)) {
            sb.replace(data.startIndex() - removeCharCount, data.endIndex() - removeCharCount, replace);
            removeCharCount += data.word().length() - 1;
        }
        return sb.toString();
    }

}
