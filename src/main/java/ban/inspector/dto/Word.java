package ban.inspector.dto;

public record Word(String word, int startIndex, int endIndex) {

    public boolean isInclude(Word word) {
        return word.startIndex <= startIndex && word.endIndex >= endIndex;
    }

    @Override
    public String toString() {
        return String.format("word = %s, startIndex = %d, endIndex = %d", word, startIndex, endIndex);
    }
}
