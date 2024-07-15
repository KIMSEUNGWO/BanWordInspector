package ban.inspector.dto;

public class Word implements Comparable<Word> {

    private final String word;
    private final int startIndex;
    private final int endIndex;

    public Word(String word, int startIndex) {
        this.word = word;
        this.startIndex = startIndex;
        this.endIndex = startIndex + word.length();
    }

    @Override
    public String toString() {
        return String.format("word = %s, startIndex = %d, endIndex = %d", word, startIndex, endIndex);
    }

    public boolean isIncludeRange(int start, int end) {
        return start <= startIndex && end >= endIndex;
    }
    public boolean isInclude(Word word) {
        return word.startIndex <= startIndex && word.endIndex >= endIndex;
    }

    @Override
    public int compareTo(Word o) {
        if (startIndex - o.startIndex != 0) return startIndex - o.startIndex;
        if (endIndex - o.endIndex != 0) return endIndex - o.endIndex;
        return word.compareTo(o.word);
    }

    public String getWord() {
        return word;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }
}
