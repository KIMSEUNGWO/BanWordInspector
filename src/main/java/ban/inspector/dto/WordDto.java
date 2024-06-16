package ban.inspector.dto;

import lombok.Getter;

@Getter
public class WordDto {

    private final String word;
    private final int startIndex;
    private final int endIndex;

    public WordDto(String word, int startIndex) {
        this.word = word;
        this.startIndex = startIndex;
        this.endIndex = startIndex + word.length();
    }

    @Override
    public String toString() {
        return String.format("word = %s, startIndex = %d, endIndex = %d", word, startIndex, endIndex);
    }
}
