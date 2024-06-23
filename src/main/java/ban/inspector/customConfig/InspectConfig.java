package ban.inspector.customConfig;

public interface InspectConfig {

    default void addBanWords(WordFactory factory) {}
    default void addExceptWords(WordFactory factory) {}
}
