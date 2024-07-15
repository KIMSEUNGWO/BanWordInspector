package ban.inspector.updater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WordCheckerImpl implements WordChecker {

    private final WordUpdater updater;

    @Autowired
    public WordCheckerImpl(WordUpdater updater) {
        this.updater = updater;
    }

    @Override
    public List<String> getDefaultBanWords() {
        return updater.getDefaultBanWords();
    }

    @Override
    public List<String> getDefaultExceptWords() {
        return updater.getDefaultExceptWords();
    }

}
