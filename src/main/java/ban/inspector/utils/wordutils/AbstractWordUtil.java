package ban.inspector.utils.wordutils;

import ban.inspector.utils.WordUtil;

public abstract class AbstractWordUtil<T extends AbstractWordUtil<T>> {

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

    public abstract T get();
}
