package ban.inspector.utils;

public interface WordUtil {
    default void addWord(String word) {
        push(word, 0);
    }
    void push(String word, int index);
    String find(String str, int idx);
    WordUtil getInstance();

}
