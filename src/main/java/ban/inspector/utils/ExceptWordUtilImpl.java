package ban.inspector.utils;


import ban.inspector.dto.WordDto;

import java.util.List;

public class ExceptWordUtilImpl extends ExceptWordUtil {


    @Override
    public List<WordDto> expectFilter(String newWord, List<WordDto> beforeWords) {
        int lastIndex = beforeWords.get(beforeWords.size() - 1).getEndIndex();
        for (int i = 0; i < lastIndex; i++) {
            int txt = find(newWord, i);
            if (txt != -1) {
                remove(i, i + txt, beforeWords);
                i += txt - 1;
            }
        }
        return beforeWords;
    }

    private void remove(int startIndex, int endIndex, List<WordDto> beforeWords) {
        for (int i = 0; i < beforeWords.size(); i++) {
            WordDto wordDto = beforeWords.get(i);
            if (wordDto.includeRange(startIndex, endIndex)) {
                beforeWords.remove(i);
                return;
            }
            if (wordDto.getStartIndex() >= endIndex) return;
        }
    }
}
