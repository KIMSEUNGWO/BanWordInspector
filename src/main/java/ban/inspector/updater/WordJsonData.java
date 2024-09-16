package ban.inspector.updater;

import java.util.Collections;
import java.util.List;

public class WordJsonData {

    private String version;
    private List<String> words = Collections.emptyList();

    @Override
    public String toString() {
        return "version : " + version + ", words : " + words;
    }

    public WordJsonData() {
    }

    public WordJsonData(String version) {
        this.version = version;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }
}