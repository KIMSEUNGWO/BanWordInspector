package ban.inspector.utils.wordutils;

import ban.inspector.utils.WordUtil;

public abstract class AbstractWordUtil {

    protected final WordUtil wordUtil;

    public AbstractWordUtil(WordUtil wordUtil) {
        this.wordUtil = wordUtil;
    }

    public void addWord(String word) {
        wordUtil.addWord(word);
    }


    public void build() {
        wordUtil.build();
    }

}
