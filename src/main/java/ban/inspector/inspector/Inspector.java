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
        List<Word> data = inspect(word);
        for (int i = data.size() - 1; i >= 0; i--) {
            sb.replace(data.get(i).startIndex(), data.get(i).endIndex(), replace);
        }
        return sb.toString();
    }

}
