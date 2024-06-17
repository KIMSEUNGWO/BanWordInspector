package ban.inspector.utils;

public interface WordUtil {

    default void addWord(String word) { push(word, 0);}

    void push(String word, int index);

    int find(String str, int index, int deep, boolean ignoreSpace);

}
