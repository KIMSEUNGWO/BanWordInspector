package ban.inspector.updater;

import java.util.List;

public interface WordUpdater {

    List<String> getDefaultBanWords();
    List<String> getDefaultExceptWords();

}
