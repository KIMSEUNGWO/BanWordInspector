package ban.inspector.utils;

import ban.inspector.dto.Word;

import java.util.ArrayList;
import java.util.List;

public abstract class BanWordUtil extends WordUtilImpl implements WordUtilSettings {

    public final List<Word> filter(String word) {
        String newWord = setWordForm(word);
        return useToArray(newWord);
    }

    protected abstract String setWordForm(String word);

    @Override
    public boolean setIgnoreSpace() {
        return true;
    }

    private List<Word> useToArray(String word) {
        List<Word> words = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            int txt = find(word, i, 0, setIgnoreSpace());
            if (txt != -1) {
                words.add(new Word(word.substring(i, i + txt), i));
                i += txt - 1;
            }
            if (count++ >= word.length()) {
                System.out.println("BanWordUtil.useToArray : StackOverflow");
                return new ArrayList<>();
            }
        }
        return words;
    }

}
