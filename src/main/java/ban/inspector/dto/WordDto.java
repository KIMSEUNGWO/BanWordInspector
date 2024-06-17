package ban.inspector.dto;

import lombok.Getter;

import java.util.Comparator;

@Getter
public class WordDto implements Comparable<WordDto> {

    private final String word;
    private final int startIndex;
    private final int endIndex;

    public WordDto(String word, int startIndex) {
        this.word = word;
        this.startIndex = startIndex;
        this.endIndex = startIndex + word.length();
    }

    public boolean isSame(StringBuilder sb) {
        if (sb.length() < endIndex) return false;
        return word.equals(sb.substring(startIndex, endIndex));
    }

    @Override
    public String toString() {
        return String.format("word = %s, startIndex = %d, endIndex = %d", word, startIndex, endIndex);
    }

    public boolean includeRange(int start, int end) {
        return start <= startIndex && end >= endIndex;
    }

    @Override
    public int compareTo(WordDto o) {
        if (startIndex - o.startIndex != 0 ) return startIndex - o.startIndex;
        if (endIndex - o.endIndex != 0) return endIndex - o.endIndex;
        return word.compareTo(o.word);
    }
}
