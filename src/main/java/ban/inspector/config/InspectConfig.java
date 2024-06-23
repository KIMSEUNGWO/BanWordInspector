package ban.inspector.config;

import ban.inspector.factory.WordFactory;

public interface InspectConfig {

    default void addBanWords(WordFactory factory) {}
    default void addExceptWords(WordFactory factory) {}
}
