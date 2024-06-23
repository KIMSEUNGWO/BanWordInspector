package ban.inspector.customConfig.innerConfig;

import java.util.List;

public interface WordUpdater {

    void update();
    List<String> getDefaultBanWords();
    List<String> getDefaultExceptWords();
}
