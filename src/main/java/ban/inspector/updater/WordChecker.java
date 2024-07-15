package ban.inspector.updater;

import java.util.List;

public interface WordChecker {

    List<String> getDefaultBanWords();
    List<String> getDefaultExceptWords();
}
