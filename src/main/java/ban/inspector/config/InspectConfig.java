package ban.inspector.config;

import java.util.List;

public interface InspectConfig {

    List<String> initBanWords();
    List<String> initExceptWords();

}
