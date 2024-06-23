package ban.inspector.updater;

import java.util.List;

public interface WordUpdater {

    void update();
    List<String> getDefaultBanWords();
    List<String> getDefaultExceptWords();
}
