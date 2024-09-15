package ban.inspector.updater;

import java.util.List;

public interface WordLoader {

    List<String> readBanWords();
    List<String> readExceptWords();
}
