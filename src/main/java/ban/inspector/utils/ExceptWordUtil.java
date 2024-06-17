package ban.inspector.utils;

import ban.inspector.dto.Word;

import java.util.List;

public abstract class ExceptWordUtil extends WordUtilImpl implements WordUtilSettings {

    public final List<Word> filter(String newWord, List<Word> beforeWords) {
        return (beforeWords.isEmpty()) ? List.of() : expectFilter(newWord, beforeWords);
    }

    private List<Word> expectFilter(String newWord, List<Word> beforeWords) {
        int startIndex = Math.max(beforeWords.get(0).getStartIndex() - 5, 0);
        int lastIndex = beforeWords.get(beforeWords.size() - 1).getEndIndex();
        for (int i = startIndex; i < lastIndex; i++) {
            int idx = find(newWord, i, 0, setIgnoreSpace());
            if (idx != -1) {
                remove(i, i + idx, beforeWords);
                i += idx - 1;
            }
        }
        return beforeWords;
    }

    private void remove(int startIndex, int endIndex, List<Word> beforeWords) {
        for (int i = 0; i < beforeWords.size(); i++) {
            Word word = beforeWords.get(i);
            if (word.includeRange(startIndex, endIndex)) {
                beforeWords.remove(i);
                return;
            }
            if (word.getStartIndex() >= endIndex) return;
        }
    }

    @Override
    public boolean setIgnoreSpace() {
        return false;
    }
}
